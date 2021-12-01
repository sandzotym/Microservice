package com.microservice.os.api.service;

import com.microservice.os.api.commons.Payment;
import com.microservice.os.api.commons.TransactionRequest;
import com.microservice.os.api.commons.TransactionResponse;
import com.microservice.os.api.entity.Order;
import com.microservice.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo; //Injecting
    @Autowired
    private RestTemplate template;

    String response = "";

    // For Standalone Order Service
    //public Order saveOrder(Order order) { return repo.save(order);}

    public TransactionResponse saveOrder(TransactionRequest request) throws Exception {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        // REST Call to Payment & pass OrderID
        // Payment payResponse = template.postForObject("http://localhost:9192/payment/doPayment", payment, Payment.class);
        // Integrate with Service Registry i.e. Eureka & give the URL Name
        Payment payResponse = template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
        assert payResponse != null;
        response = ("success".equalsIgnoreCase(payResponse.getPaymentStatus())
                ? "Payment Successful & Order Placed" : "There is a Failure in Payment API, Order added to Cart");
        //Hystrix to Automate Error Message
        repo.save(order);
        return new TransactionResponse(order, payResponse.getAmount(), payResponse.getTransactionId(), response);
    }
}

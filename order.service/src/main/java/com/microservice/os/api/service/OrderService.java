package com.microservice.os.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.os.api.commons.Payment;
import com.microservice.os.api.commons.TransactionRequest;
import com.microservice.os.api.commons.TransactionResponse;
import com.microservice.os.api.entity.Order;
import com.microservice.os.api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope  //to pick up updated properties without restarting the config server (Refresh Curl needs to be fired).
public class OrderService {

    @Autowired
    private OrderRepository repo; //Injecting
    @Autowired
    @Lazy
    private RestTemplate template;

    String response = "";

    @Value("${microservice.payment.service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    Logger log = LoggerFactory.getLogger(OrderService.class);

    // For Standalone Order Service
    //public Order saveOrder(Order order) { return repo.save(order);}

    public TransactionResponse saveOrder(TransactionRequest request) throws Exception {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        // REST Call to Payment & pass OrderID
        // Payment payResponse = template.postForObject("http://localhost:9192/payment/doPayment", payment, Payment.class);
        // Payment payResponse = template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
        // Integrate with Service Registry i.e. Eureka & give the URL Name, also then upgrade to use Cloud Config
        log.info("OrderService Request : {}", new ObjectMapper().writeValueAsString(request));
        Payment payResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);
        assert payResponse != null;
        response = ("success".equalsIgnoreCase(payResponse.getPaymentStatus())
                ? "Payment Successful & Order Placed" : "There is a Failure in Payment API, Order added to Cart");
        //Hystrix to Automate Error Message
        log.info("PaymentService Response from OrderService REST Call : {}", new ObjectMapper().writeValueAsString(payResponse));
        repo.save(order);
        return new TransactionResponse(order, payResponse.getAmount(), payResponse.getTransactionId(), response);
    }
}

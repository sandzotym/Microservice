package com.microservice.ps.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.ps.api.entity.Payment;
import com.microservice.ps.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    Logger log = LoggerFactory.getLogger(PaymentService.class);

    public Payment savePayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("PaymentService Request : {}", new ObjectMapper().writeValueAsString(payment));
        return repository.save(payment);
    }

    /*
    API should be 3rd Party Gateway like PayPal, PayTm, etc.
     */
    public String paymentProcessing() {
        return new Random().nextBoolean() ? "success" : "false";
    }

    public Payment findPaymentHistoryByOrderID(String orderId) throws JsonProcessingException {
        Payment payment = repository.findByOrderId(orderId);
        log.info("PaymentService findPaymentHistoryByOrderID : {}", new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}

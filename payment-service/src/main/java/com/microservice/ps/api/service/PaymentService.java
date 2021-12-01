package com.microservice.ps.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.ps.api.entity.Payment;
import com.microservice.ps.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment savePayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }

    /*
    API should be 3rd Party Gateway like PayPal, PayTm, etc.
     */
    public String paymentProcessing() {
        return new Random().nextBoolean() ? "success" : "false";
    }

    public Payment findPaymentHistoryByOrderID(String orderId) { return repository.findByOrderId(orderId); }
}

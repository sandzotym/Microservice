package com.microservice.ps.api.repository;

import com.microservice.ps.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByOrderId(String orderId);
}

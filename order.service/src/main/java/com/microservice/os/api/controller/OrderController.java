package com.microservice.os.api.controller;

import com.microservice.os.api.commons.TransactionRequest;
import com.microservice.os.api.commons.TransactionResponse;
import com.microservice.os.api.entity.Order;
import com.microservice.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/bookOrder")
    //public Order bookOrder(@RequestBody Order order) { return service.saveOrder(order); }
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws Exception {
        return service.saveOrder(request);
    }
}

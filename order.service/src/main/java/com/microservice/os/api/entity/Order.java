package com.microservice.os.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Order_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id //PK
    private String id;
    private String name;
    private int qty;
    private double price;
    //As Lombok dependency is added, no getters/setters required
}

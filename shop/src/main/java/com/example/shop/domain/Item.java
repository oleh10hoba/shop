package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Double price;
    private Instant createdAt;
    private Instant updatedAt;

    public Item() {}

    private Item(UUID id,
                     String name,
                     Double price,
                     Instant createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }



    public static Item add(UUID id,
                               String name,
                               Double price,
                               Instant createdAt) {
        return new Item(
                id,
                name,
                price,
                createdAt);
    }

    public void update(String name,
                       Double price,
                       Instant updatedAt) {
        this.name = name;
        this.price = price;
        this.updatedAt = updatedAt;
    }
}
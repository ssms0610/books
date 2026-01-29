package com.yonsai.books.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 배송 방법
    @Column(nullable = false)
    private String deliveryType;

    // 배송비
    @Column(nullable = false)
    private int deliveryPrice;

    // 기본 생성자
    protected Delivery() {
    }

    // 생성자
    public Delivery(String deliveryType, int deliveryPrice) {
        this.deliveryType = deliveryType;
        this.deliveryPrice = deliveryPrice;
    }

    // getter
    public Long getId() {
        return id;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

}

package com.yonsai.books.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity // JPA 관리 객체
@Table(name = "book")
public class Book {

    @Id // 테이블의 기본키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동 증가
    private Long id;

    // 도서명
    @Column(nullable = false)
    private String title;

    // 도서 가격
    @Column(nullable = false)
    private int price;

    // 배송정보 (1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // 기본 생성자
    // protected : 외부에서 사용 X, JPA는 접근 가능
    protected Book() {
    }

    // 생성자
    public Book(String title, int price, Delivery delivery) {
        this.title = title;
        this.price = price;
        this.delivery = delivery;
    }

    // getter
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public Delivery getDelivery() {
        return delivery;
    }
    // // setter 대신 -> 수정용 메서드
    // public void update(String title, String author) {
    // if (title != null)
    // this.title = title;
    // if (author != null)
    // this.author = author;
    // }

}

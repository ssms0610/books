package com.yonsai.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonsai.books.entity.Book;
import com.yonsai.books.entity.Delivery;
import com.yonsai.books.repository.BookRepository;

// @RestController // 메서드의 반환값을 뷰로 보지 않고 바로 JSON형태로 응답하게 해줌
@Controller
@RequestMapping("/books")
public class BookController {

    // final : 생성자에서 1번만 주입받고 바뀌지 않게 함
    private final BookRepository bookRepository;

    // 생성자
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 등록 화면
    @GetMapping("/add")
    public String addForm() {
        return "book-form";
    }

    // 등록 처리
    // -> 폼에서 넘어온 값들을 파라미터로 받음
    @PostMapping("/add")
    public String addBook(@RequestParam String title, @RequestParam int price,
            @RequestParam String deliveryType, @RequestParam int deliveryPrice) {

        // 배송정보 엔티티 생성
        Delivery delivery = new Delivery(deliveryType, deliveryPrice);
        // 도서 엔티티 생성
        Book book = new Book(title, price, delivery);
        // DB에 저장
        bookRepository.save(book);

        return "redirect:/books/add";
    }

}
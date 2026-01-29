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

// // 도서 등록(add)
// @PostMapping("/add")
// // Map : 키-값 형태로 데이터를 담는 인터페이스
// // @RestController라 JSON으로 나감
// // @RequestParam : 요청 파라미터(title)값을 받아 title 변수에 넣는다
// public Map<String, Object> add(@RequestParam String title, @RequestParam
// String author) {

// // 서비스 X -> 등록된 것처럼 응답만
// // -> 응답으로 보낼 데이터 맵을 만듬
// Map<String, Object> res = new HashMap<>();
// res.put("action", "add");
// res.put("title", title);
// res.put("author", author);
// res.put("result", "OK"); // 성공했다는 결과값

// return res;
// }

// // 도서 수정(update)
// @PutMapping("/update")
// // required = false : 없어도 된다
// // -> 수정할 때 title만 바꾸거나 author만 바꾸는 케이스를 허용하기 위해서(없으면 null)
// public Map<String, Object> update(@RequestParam Long id,
// @RequestParam(required = false) String title,
// @RequestParam(required = false) String author) {

// // 수정 응답용 Map 생성
// Map<String, Object> res = new HashMap<>();
// res.put("action", "update");
// res.put("id", id);
// res.put("title", title);
// res.put("author", author);
// res.put("result", "OK");

// return res;
// }

// // 도서 조회(select)
// @GetMapping("/select")
// // id를 조회받아서 조회 결과를 만들어 반환
// public Map<String, Object> select(@RequestParam Long id) {

// // 조회 응답 Map 생성
// Map<String, Object> res = new HashMap<>();
// res.put("action", "select");
// res.put("id", id);
// res.put("title", "더미도서");
// res.put("author", "더미저자");

// return res;
// }
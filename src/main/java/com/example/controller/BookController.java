package com.example.controller;

import com.example.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {

    // Mock data dữ liệu sách
    private List<Book> getMockBooks() {
        return Arrays.asList(
                new Book(1, "Lập trình Spring MVC", "Nguyễn Văn A", 150000),
                new Book(2, "Cấu trúc dữ liệu", "Trần Văn B", 320000), // Cuốn này sẽ bị Highlight (> 300k)
                new Book(3, "Trí tuệ nhân tạo cơ bản", "Lê Thị C", 450000), // Highlight
                new Book(4, "Lịch sử thế giới", "Phạm Văn D", 120000)
        );
    }

    @GetMapping(value = {"/", "/books"})
    public String showBookList(Model model) {
        model.addAttribute("books", getMockBooks());
        return "book-list"; // Trả về view danh sách
    }

    @GetMapping("/books/{id}")
    public String showBookDetail(@PathVariable("id") int id, Model model) {
        Book selectedBook = getMockBooks().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
        
        model.addAttribute("book", selectedBook);
        return "book-detail"; // Trả về view chi tiết
    }
}

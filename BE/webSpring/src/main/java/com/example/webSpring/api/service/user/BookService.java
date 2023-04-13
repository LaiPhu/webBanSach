package com.example.webSpring.api.service.user;

import com.example.webSpring.api.dto.user.BookDTO;
import com.example.webSpring.api.mapper.user.BookMapper;
import com.example.webSpring.api.model.user.Book;
import com.example.webSpring.api.repository.user.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDTO> getListBookByCategoryId(String categoryId){
        List<Book> books = bookRepository.findAllByCategoryIdOrderByCreatedDateDesc(categoryId);
        List<BookDTO> bookDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(books)) {
            books.forEach(
                    book -> {
                        BookDTO bookDTO = BookMapper.INSTANCE.toDto(book);
                        bookDTOS.add(bookDTO);
                    }
            );
        }
        return bookDTOS;
    }
}

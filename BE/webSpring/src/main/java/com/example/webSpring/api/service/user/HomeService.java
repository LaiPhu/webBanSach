package com.example.webSpring.api.service.user;

import com.example.webSpring.api.core.exception.BusinessException;
import com.example.webSpring.api.core.exception.ExceptionType;
import com.example.webSpring.api.dto.user.BookDTO;
import com.example.webSpring.api.mapper.user.BookMapper;
import com.example.webSpring.api.model.user.Book;
import com.example.webSpring.api.model.user.Category;
import com.example.webSpring.api.model.user.OrderDetail;
import com.example.webSpring.api.repository.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final PublishingCompanyRepository publishingCompanyRepository;
    private final OrderDetailRepository orderDetailRepository;

    public List<BookDTO> getListBook(){
        return null;

    }

    public List<String> getListCategory(){
        List<Category> categories = categoryRepository.findAll();
        List<String> categoryNames = new ArrayList<>();
        if(!CollectionUtils.isEmpty(categories)){
            categories.forEach(
                    category -> {
                        categoryNames.add(category.getName());
                    }
            );
        }
        return categoryNames;
    }

    public List<BookDTO> getListBookSelling(){
        List<OrderDetail> orderDetails = orderDetailRepository.findTop10BooksByNumber(10);
        List<BookDTO> bookDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(orderDetails)){
            orderDetails.forEach(
                    orderDetail -> {
                        Book book = bookRepository.findById(orderDetail.getBookId())
                                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_BOOK.getMessageKey()));
                        BookDTO bookDTO = BookMapper.INSTANCE.toDto(book);
                        bookDTOS.add(bookDTO);
                    }
            );
        }
        return bookDTOS;
    }

    public List<BookDTO> getListBookSelling3(){
        List<OrderDetail> orderDetails = orderDetailRepository.findTop10BooksByNumber(3);
        List<BookDTO> bookDTOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(orderDetails)){
            orderDetails.forEach(
                    orderDetail -> {
                        Book book = bookRepository.findById(orderDetail.getBookId())
                                .orElseThrow(() -> new BusinessException(ExceptionType.NOT_FOUND_BOOK.getMessageKey()));
                        BookDTO bookDTO = BookMapper.INSTANCE.toDto(book);
                        bookDTOS.add(bookDTO);
                    }
            );
        }else{
            List<Book> books = bookRepository.findAllByOrderByCreatedDateDesc();
            for(int i = 0; i < 3; i++){
                BookDTO bookDTO = BookMapper.INSTANCE.toDto(books.get(i));
                bookDTOS.add(bookDTO);
            }
        }
        return bookDTOS;
    }

    public List<BookDTO> getListBookUpdate(){
        List<Book> books = bookRepository.findAllByOrderByCreatedDateDesc();
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

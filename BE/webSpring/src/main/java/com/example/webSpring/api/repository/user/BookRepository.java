package com.example.webSpring.api.repository.user;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepository;
import com.example.webSpring.api.model.user.Book;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@JaversSpringDataAuditable
@EnableMongoRepositories
public interface BookRepository extends SoftDeletionMongoRepository<Book, String>, BookRepositoryCustom {
    List<Book> findAllByOrderByCreatedDateDesc();
    List<Book> findAllByCategoryIdOrderByCreatedDateDesc(String categoryId);
}

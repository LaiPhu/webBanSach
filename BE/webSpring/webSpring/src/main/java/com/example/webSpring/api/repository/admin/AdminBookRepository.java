package com.example.webSpring.api.repository.admin;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepository;
import com.example.webSpring.api.model.admin.AdminBook;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@JaversSpringDataAuditable
@EnableMongoRepositories
public interface AdminBookRepository extends SoftDeletionMongoRepository<AdminBook, String>, AdminBookRepositoryCustom {
    List<AdminBook> findAllByOrderByCreatedDateDesc();
    List<AdminBook> findAllByCategoryIdOrderByCreatedDateDesc(String categoryId);
    List<AdminBook> findAllByNameIgnoreCaseOrderByCreatedDateDesc(String name);
}

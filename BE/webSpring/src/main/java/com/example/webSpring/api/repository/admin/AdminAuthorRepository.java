package com.example.webSpring.api.repository.admin;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepository;
import com.example.webSpring.api.model.admin.AdminAuthor;
import com.example.webSpring.api.model.user.Author;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@JaversSpringDataAuditable
@EnableMongoRepositories
public interface AdminAuthorRepository extends SoftDeletionMongoRepository<AdminAuthor, String>, AdminAuthorRepositoryCustom {
    List<AdminAuthor> findAllByNameIgnoreCaseOrderByCreatedDateDesc(String name);
}

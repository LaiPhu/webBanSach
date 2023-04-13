package com.example.webSpring.api.repository.admin;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepository;
import com.example.webSpring.api.model.admin.AdminCategory;
import com.example.webSpring.api.model.user.Category;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@JaversSpringDataAuditable
@EnableMongoRepositories
public interface AdminCategoryRepository extends SoftDeletionMongoRepository<AdminCategory, String>, AdminCategoryRepositoryCustom {
}

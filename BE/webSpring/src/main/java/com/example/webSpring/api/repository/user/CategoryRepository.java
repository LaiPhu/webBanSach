package com.example.webSpring.api.repository.user;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepository;
import com.example.webSpring.api.model.user.Category;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@JaversSpringDataAuditable
@EnableMongoRepositories
public interface CategoryRepository extends SoftDeletionMongoRepository<Category, String>, CategoryRepositoryCustom {
}

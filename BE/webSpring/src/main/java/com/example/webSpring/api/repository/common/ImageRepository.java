package com.example.webSpring.api.repository.common;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepository;
import com.example.webSpring.api.model.common.Image;
import com.example.webSpring.api.model.user.Author;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@JaversSpringDataAuditable
@EnableMongoRepositories
public interface ImageRepository extends SoftDeletionMongoRepository<Image, String>, ImageRepositoryCustom {

}

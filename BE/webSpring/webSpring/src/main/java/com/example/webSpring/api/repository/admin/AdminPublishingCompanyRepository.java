package com.example.webSpring.api.repository.admin;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepository;
import com.example.webSpring.api.model.admin.AdminPublishingCompany;
import com.example.webSpring.api.model.user.PublishingCompany;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@JaversSpringDataAuditable
@EnableMongoRepositories
public interface AdminPublishingCompanyRepository extends SoftDeletionMongoRepository<AdminPublishingCompany, String>, AdminPublishingCompanyRepositoryCustom {
}

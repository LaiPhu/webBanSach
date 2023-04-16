package com.example.webSpring.api.repository.admin;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepository;
import com.example.webSpring.api.model.admin.AdminPublishingCompany;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@JaversSpringDataAuditable
@EnableMongoRepositories
public interface AdminPublishingCompanyRepository extends SoftDeletionMongoRepository<AdminPublishingCompany, String>, AdminPublishingCompanyRepositoryCustom {
    List<AdminPublishingCompany> findAllByNameIgnoreCaseOrderByCreatedDateDesc(String name);
}

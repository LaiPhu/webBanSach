/*
 * Copyright (c) 2021. Pitagon., JSC. All rights reserved.
 */

package com.example.webSpring.api.config;

import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepositoryFactoryBean;
import com.example.webSpring.api.core.mongo.SoftDeletionMongoRepositoryImpl;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.webSpring.api.repository",
        repositoryBaseClass = SoftDeletionMongoRepositoryImpl.class,
        repositoryFactoryBeanClass = SoftDeletionMongoRepositoryFactoryBean.class
)

@Import(value = MongoAutoConfiguration.class)
public class DatabaseConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Override
    protected String getDatabaseName() {
        return mongoDatabase;
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }
}

package com.sillock.core.config;

import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "sillog";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
package com.sillock.core.config;

import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

public class MongoConfig extends AbstractMongoClientConfiguration {

    // rest of the config goes here

    @Override
    protected String getDatabaseName() {
        return "sillog";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
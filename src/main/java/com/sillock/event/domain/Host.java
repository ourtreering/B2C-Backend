package com.sillock.event.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Host {
    @Column(name = "host")
    private String value;

    protected Host(){}

    public Host(String host) {
        this.value = host;
    }

    public boolean Host(String host) {
        return this.value.equals(host);
    }
}
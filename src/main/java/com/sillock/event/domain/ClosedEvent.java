package com.sillock.event.domain;

import javax.persistence.Column;

public class ClosedEvent extends Event{
    @Column(name="search_keyword")
    private String searchKeyword;
}
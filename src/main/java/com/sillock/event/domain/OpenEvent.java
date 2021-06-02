package com.sillock.event.domain;

import javax.persistence.Embedded;

public class OpenEvent extends Event{
    @Embedded
    private Host host;
}
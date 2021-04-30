package com.sillock.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class IssuedToken{

    @EmbeddedId
    private IssuedTokenPK memberId;

}



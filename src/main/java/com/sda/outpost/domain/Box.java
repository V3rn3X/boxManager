package com.sda.outpost.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Box {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;


}

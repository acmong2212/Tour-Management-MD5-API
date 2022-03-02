package com.ducthang.demoApiAngular.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private String price;
    private String description;

    @ManyToOne
    Category category;
}

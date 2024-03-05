package com.surjeet.recipesharingapp.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    private User user;

    private String image;

    private String description;

    private boolean vegitarian;

    private LocalDateTime createdAt;

    private List<Long> likes=new ArrayList<>();

}

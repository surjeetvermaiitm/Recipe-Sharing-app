package com.surjeet.recipesharingapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name="user_table")
@SequenceGenerator(name = "user_table_seq", sequenceName = "user_table_seq", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;

    private String email;

    private String fullName;
}

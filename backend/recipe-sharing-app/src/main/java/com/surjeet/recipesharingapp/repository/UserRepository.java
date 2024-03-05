package com.surjeet.recipesharingapp.repository;

import com.surjeet.recipesharingapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);
}

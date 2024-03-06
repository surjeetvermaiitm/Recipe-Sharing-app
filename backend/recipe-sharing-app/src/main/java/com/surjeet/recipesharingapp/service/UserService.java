package com.surjeet.recipesharingapp.service;

import com.surjeet.recipesharingapp.models.User;

public interface UserService {

    public User findUserById(Long userId) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;


}

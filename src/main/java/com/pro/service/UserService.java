package com.pro.service;

import com.pro.entity.User;

import java.util.List;

/**
 * Created by paul on 2017/11/23.
 */
public interface UserService {
    List<User> serlectAll();

    User findById(String id);
}

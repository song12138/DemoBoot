package com.pro.service.impl;

import com.pro.dao.UserDao;
import com.pro.entity.User;
import com.pro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by paul on 2017/11/23.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;


    @Override
    public List<User> serlectAll() {
        return userDao.selectAll();
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

}

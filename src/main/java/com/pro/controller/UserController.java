package com.pro.controller;

import com.pro.entity.User;
import com.pro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by paul on 2017/11/23.
 */
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/all")
    public List<User> getAll(){
        logger.error("***********************************************");
        return userService.serlectAll();
    }

    @RequestMapping("/{id}")
    public User getById(@PathVariable String id){
        return userService.findById(id);
    }

}

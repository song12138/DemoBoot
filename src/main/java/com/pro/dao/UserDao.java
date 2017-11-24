package com.pro.dao;


import com.pro.config.MyMapper;
import com.pro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by paul on 2017/11/23.
 */
@Mapper
public interface UserDao extends MyMapper<User> {
    User findById(@Param("id") String id);
}

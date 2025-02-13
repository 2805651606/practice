package org.javaboy.dd.service.impl;

import org.apache.ibatis.annotations.Select;
import org.javaboy.dd.mapper.UserMapper;
import org.javaboy.dd.model.User;
import org.javaboy.dd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
}

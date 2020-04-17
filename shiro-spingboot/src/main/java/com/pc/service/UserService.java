package com.pc.service;


import com.pc.vo.User;

public interface UserService {

    User queryUserByUsername(String username);

    int register(User user);
}
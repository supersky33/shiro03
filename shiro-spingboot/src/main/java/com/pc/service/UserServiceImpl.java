package com.pc.service;

import com.pc.mapper.UserMapper;
import com.pc.vo.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    public int register(User user) {
        String salt = UUID.randomUUID().toString();
        String token = new Sha256Hash(user.getPassword(), salt, 1000).toBase64();
        user.setPassword(token);
        user.setSalt(salt);
        return userMapper.insertSelective(user);
    }
}
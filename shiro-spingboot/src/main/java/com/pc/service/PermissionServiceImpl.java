package com.pc.service;

import com.pc.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionMapper permissionMapper;

    public List<String> queryAllPermissionNameByUsername(String username) {
        return permissionMapper.queryAllPermissionNameByUsername(username);
    }
}
package com.pc.service;

import com.pc.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    public List<String> queryAllRoleNameByUsername(String username) {
        return roleMapper.queryAllRoleNameByUsername(username);
    }
}
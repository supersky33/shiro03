package com.pc.service;

import java.util.List;

public interface RoleService {

    List<String> queryAllRoleNameByUsername(String username);
}
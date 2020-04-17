package com.pc.service;

import java.util.List;

public interface PermissionService {

    List<String> queryAllPermissionNameByUsername(String username);
}
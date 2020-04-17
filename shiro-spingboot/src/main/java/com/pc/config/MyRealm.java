package com.pc.config;

import com.pc.mapper.UserRoleMapper;
import com.pc.service.PermissionService;
import com.pc.service.PermissionServiceImpl;
import com.pc.service.RoleService;
import com.pc.service.UserService;
import com.pc.vo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    /**
     * 权限、角色查询
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限、角色查询");
        String username = principalCollection.getPrimaryPrincipal().toString();
        List<String> roles = roleService.queryAllRoleNameByUsername(username);
        List<String> perms = permissionService.queryAllPermissionNameByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(new HashSet<>(roles));
        simpleAuthorizationInfo.setStringPermissions(new HashSet<>(perms));
        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证");
        String username = authenticationToken.getPrincipal().toString();
        User user = userService.queryUserByUsername(username);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
    }
}

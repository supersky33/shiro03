package com.pc.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    // shiroFilterFactoryBean:3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro的内置过滤器
        /**
         * anon, authc, user(remember me), perms, roles
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/user/add", "roles[admin,seller]");

        //filterMap.put("/user/add", "perms[user:query]");
        filterMap.put("/user/update", "perms[user:delete]");

        filterMap.put("/user/*", "authc");
        //filterMap.put("/user/update", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }

    // DafaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("myRealm") MyRealm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //密码比较器
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("sha-256");
        hashedCredentialsMatcher.setHashIterations(1000);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
        return hashedCredentialsMatcher;
    }

    // Realm
    @Bean(name = "myRealm")
    public MyRealm myRealm(@Qualifier("credentialsMatcher")HashedCredentialsMatcher hashedCredentialsMatcher ) {
        MyRealm m = new MyRealm();
        m.setCredentialsMatcher(hashedCredentialsMatcher);
        return m;
    }
}

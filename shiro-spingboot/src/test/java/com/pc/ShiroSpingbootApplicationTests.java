package com.pc;

import com.pc.service.UserServiceImpl;
import com.pc.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpingbootApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testUser() {
        User user = userService.queryUserByUsername("pc");
        System.out.println("==>username:" + user.getUsername());
        System.out.println("==>passeord:" + user.getPassword());
        System.out.println("==>salt:" + user.getSalt());
    }

}

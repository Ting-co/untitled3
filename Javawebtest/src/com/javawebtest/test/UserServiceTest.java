package com.javawebtest.test;

import com.javawebtest.bean.User;
import com.javawebtest.service.UserService;
import com.javawebtest.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "bdfssdf8", "555666", "1bbj168@qq.com"));
        userService.registUser(new User(null, "adfsdf8", "777666", "2abc168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println( userService.login(new User(null, "wzg168", "123456", null)) );
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("wzg16888")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用！");
        }
    }
}
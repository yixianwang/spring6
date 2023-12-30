package com.mrtutu.spring6.iocxml.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserDao {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // get corresponding bean according to interface by class
        UserDao userDao = context.getBean(UserDao.class);
        System.out.println("from interface: " + userDao);
        userDao.run();
    }
}

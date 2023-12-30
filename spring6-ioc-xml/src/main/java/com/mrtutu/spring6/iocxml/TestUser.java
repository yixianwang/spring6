package com.mrtutu.spring6.iocxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        // get bean by id
        User user1 = (User) context.getBean("user");
        System.out.println("get bean by id: " + user1);

        // get bean by class
        User user2 = context.getBean(User.class);
        System.out.println("get bean by class: " + user2);

        // get bean by id && class
        User user3 = context.getBean("user", User.class);
        System.out.println("get bean by id && class: " + user3);
    }
}

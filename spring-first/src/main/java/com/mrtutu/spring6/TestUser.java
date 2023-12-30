package com.mrtutu.spring6;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    @Test
    public void testUserObject() {
        // load spring conf file, creating object
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");

        // get object
        User user = (User)context.getBean("user");
        System.out.println(user);

        // call method of object
        user.add();
    }
}

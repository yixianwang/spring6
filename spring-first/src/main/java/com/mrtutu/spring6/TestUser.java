package com.mrtutu.spring6;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    // logger
    private Logger logger = LoggerFactory.getLogger(TestUser.class);

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

        // use logger
        logger.info("### this is info from logger!");
    }

    @Test
    public void testUserObject1() throws Exception {
        // get class name
        Class clazz = Class.forName("com.mrtutu.spring6.User");

        // create object from the class name
        // legacy: Object o = clazz.newInstance();
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        System.out.println(user);
    }
}

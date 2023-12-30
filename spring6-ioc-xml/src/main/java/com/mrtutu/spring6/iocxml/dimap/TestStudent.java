package com.mrtutu.spring6.iocxml.dimap;

import org.apache.logging.log4j.core.config.AppendersPlugin;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudent {
    @Test
    public void testStudent() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-diref.xml");
        Student student = context.getBean("studentp", Student.class);
        student.run();
    }
}

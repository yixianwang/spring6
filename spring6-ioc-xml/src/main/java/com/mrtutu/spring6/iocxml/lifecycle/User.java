package com.mrtutu.spring6.iocxml.lifecycle;

public class User {
    private String name;

    public User() {
        System.out.println("1. call constructor without params......");
    }

    public User(String name) {
        System.out.println("call constructor with params......");
        this.name = name;
    }

    // initialization method
    public void initMethod() {
        System.out.println("4. call specific init method for bean initializatino");
    }

    // deletion method
    public void destroyMethod() {
        System.out.println("7. call specific destroy method for bean destroy");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("2. set values for bean object");
        this.name = name;
    }
}

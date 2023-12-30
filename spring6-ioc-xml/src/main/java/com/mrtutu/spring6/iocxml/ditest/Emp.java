package com.mrtutu.spring6.iocxml.ditest;

public class Emp {
    private Dept dept;
    private String ename;
    private int age;

    public void work() {
        System.out.println(ename + "Emp is working......" + age);
    }
}

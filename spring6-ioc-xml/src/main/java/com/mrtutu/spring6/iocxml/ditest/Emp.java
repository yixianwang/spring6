package com.mrtutu.spring6.iocxml.ditest;

import java.util.Arrays;

public class Emp {
    private Dept dept;
    private String ename;
    private int age;

    private String[] loves;

    public void setLoves(String[] loves) {
        this.loves = loves;
    }

    public void work() {
        System.out.println(ename + "Emp is working......" + age);
        dept.info();
        System.out.println(Arrays.toString(loves));
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Dept getDept() {
        return dept;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

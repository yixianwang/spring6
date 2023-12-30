package com.mrtutu.spring6.iocxml.ditest;

public class Emp {
    private Dept dept;
    private String ename;
    private int age;

    public void work() {
        System.out.println(ename + "Emp is working......" + age);
        dept.info();
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

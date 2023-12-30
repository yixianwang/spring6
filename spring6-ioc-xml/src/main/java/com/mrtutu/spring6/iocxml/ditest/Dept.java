package com.mrtutu.spring6.iocxml.ditest;

public class Dept {
    private String dname;
    public void info() {
        System.out.println("Department name: " + dname);
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDname() {
        return dname;
    }
}

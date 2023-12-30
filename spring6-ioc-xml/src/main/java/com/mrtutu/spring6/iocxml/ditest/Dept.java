package com.mrtutu.spring6.iocxml.ditest;

import java.util.List;

public class Dept {
    private String dname;
    private List<Emp> empList;
    public void info() {
        System.out.println("Department name: " + dname);
        for (Emp emp : empList) {
            System.out.println(emp.getEname());
        }
    }

    public void setEmpList(List<Emp> empList) {
        this.empList = empList;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDname() {
        return dname;
    }
}

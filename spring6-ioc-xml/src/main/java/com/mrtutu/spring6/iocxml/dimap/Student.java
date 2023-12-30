package com.mrtutu.spring6.iocxml.dimap;

import java.util.Map;

public class Student {
    private String sid;
    private String sname;
    private Map<String, Teacher> s2t;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Map<String, Teacher> getS2t() {
        return s2t;
    }

    public void setS2t(Map<String, Teacher> s2t) {
        this.s2t = s2t;
    }

    public void run() {
        System.out.println("student id: " + sid + " student name: " + sname);
        System.out.println(s2t);
    }
}

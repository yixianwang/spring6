package com.mrtutu.spring6.autowired.service;

import com.mrtutu.spring6.autowired.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // inject dao
    // first approach: property injection
    @Autowired
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("service is running......");
        userDao.add();
    }
}

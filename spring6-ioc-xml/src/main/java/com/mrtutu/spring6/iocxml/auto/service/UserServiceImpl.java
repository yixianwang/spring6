package com.mrtutu.spring6.iocxml.auto.service;

import com.mrtutu.spring6.iocxml.auto.dao.UserDao;
import com.mrtutu.spring6.iocxml.auto.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUserService() {
        System.out.println("userService method is running......");
        userDao.addUserDao();

//        UserDao userDao = new UserDaoImpl();
//        userDao.addUserDao();
    }
}

package com.manhua.yuanzi.dao;

import com.manhua.yuanzi.YuanziApplicationTests;
import com.manhua.yuanzi.model.Admin;
import org.junit.Test;


import javax.annotation.Resource;

public class AdminDaoTest extends YuanziApplicationTests {
    @Resource
    private AdminDao adminDao;

    @Test
    public void testLogin(){
        Admin admin=adminDao.login("test1","123456");
        System.out.println(admin);

    }
}

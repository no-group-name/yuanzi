package com.manhua.yuanzi.service.imp;


import com.manhua.yuanzi.config.SHA;
import com.manhua.yuanzi.dao.AdminDao;
import com.manhua.yuanzi.model.Admin;
import com.manhua.yuanzi.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AdminServiceImp implements AdminService {
    @Resource
    private AdminDao adminDao;


    public Admin login(String username, String password){
        password= SHA.getResult(password);
        Admin admin=adminDao.login(username, password);
        return admin;
    }

    @Override
    public Admin selectAdminById(Integer id) {
        Admin admin=adminDao.selectAdminById(id);
        return admin;
    }


}
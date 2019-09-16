package com.manhua.yuanzi.service;

import com.manhua.yuanzi.model.Admin;
import org.springframework.stereotype.Service;


@Service
public interface AdminService {
    /**
     * 根据账户名和密码去数据库查询，进行登录判断
     *
     * @param username 账户名
     * @param password 密码
     * @return null表示登录失败
     */
     Admin login(String username, String password);

    /**
     * 根据id查询特特定的用户
     * @param id
     * @return
     */
    Admin selectAdminById(Integer id);

}
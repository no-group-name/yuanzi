package com.manhua.yuanzi.dao;


import com.manhua.yuanzi.model.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    /**
     * 根据账户名和密码去数据库查询登录
     * @param username 账户名
     * @param password 密码
     * @return null表示没有找到记录
     */
    Admin login(@Param(value = "username"  ) String username, @Param(value = "password") String password);

    /**
     * 根据id查询特定的用户
     * @param id ID
     * @return null表示没有找到记录
     */
    Admin selectAdminById(@Param(value = "id") Integer id);
}

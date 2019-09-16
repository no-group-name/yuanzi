package com.manhua.yuanzi.web.backstage;

import com.manhua.yuanzi.model.Admin;
import com.manhua.yuanzi.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "后台管理员个人信息管理模块")
@RestController
public class AdminControll {
    @Resource
    private AdminService adminService;



    @ApiOperation(value = "获取当前登录的管理员信息",notes = "1表示用户已经登录，0表示用户还没有登录")
    @ResponseBody
    @GetMapping(value = "/api/backstage/admin")
    @CrossOrigin(origins="http://127.0.0.1:5501",allowCredentials = "true")
    public Map<String,Object> index(HttpServletRequest request,Admin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length >0) {
            for (Cookie cookie : cookies) {
                    map.put("code", "1");//自定义值：code，1表示用户已经登录，0表示用户还没有登录
                    map.put("admin",cookie);
                    System.out.println("后 name:" + cookie.getName() + "-----value:" + cookie.getValue());
            }
        }else{
            map.put("code", "0");//自定义值：code，1表示用户已经登录，0表示用户还没有登录
            map.put("msg","请您先登录！");

        }
        return map;
   }

    @ApiOperation(value = "获取指定管理员信息",notes = "1表示查询到了指定的用户，0表示没有找到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "Id",required = true,paramType = "header",dataType = "int",defaultValue = "1"),
    })
    @ResponseBody
    @GetMapping(value = "/api/backstage/admin/selectAdminById")
    public Map<String,Object> selectAdminById(Integer id,HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        Admin admin=(Admin)session.getAttribute("admin");
            admin= adminService.selectAdminById(id);
        if (admin != null) {
            session.setAttribute("admin", admin);
            map.put("code", "1");//自定义值：code，1表示查询到了指定的用户，0表示没有找到
            map.put("admin",admin);
        } else {
            map.put("code", "0");//自定义值：code，1表示查询到了指定的用户，0表示没有找到
            map.put("msg", "该用户不存在！");
        }
        return map;
    }
}

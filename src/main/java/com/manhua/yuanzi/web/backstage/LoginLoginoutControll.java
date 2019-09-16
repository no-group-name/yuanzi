package com.manhua.yuanzi.web.backstage;

import com.manhua.yuanzi.model.Admin;
import com.manhua.yuanzi.service.AdminService;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
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

@Api(tags = "后台账户登录注销模块")
@RestController
public class LoginLoginoutControll {
    @Resource
    private AdminService adminService;



    @ApiOperation(value = "后台登录",notes = "1表示登录成功，0表示登录失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "header",dataType = "String",defaultValue = "user"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,paramType = "header",dataType = "String",defaultValue = "123456")
    })
    @ResponseBody
    @GetMapping(value = "/api/login")
    @CrossOrigin(origins="http://127.0.0.1:5501",allowCredentials = "true")
    public Map<String,Object> login( @RequestParam(value = "username",required = false) String username,@RequestParam(value = "password",required = false) String password, HttpServletResponse response) {
       Map<String, Object> map = new HashMap<String, Object>();
       Admin admin = adminService.login(username, password);
        Cookie cookie = new Cookie("username",admin.getUsername());
        cookie.setPath("/");
        response.addCookie(cookie);
       if (admin != null) {
           map.put("cookie", cookie);
           map.put("code", "1");//自定义值：status，如果为1表示登录成功，0表示登录失败
           System.out.println("前"+cookie.getValue());
       } else {
           map.put("code", "0");//自定义值：status，如果为1表示登录成功，0表示登录失败
           map.put("msg", "登录失败:账户名或密码错误");
       }
       return map;
   }

    @ApiOperation(value = "后台注销",notes = "1表示注销成功，0表示注销不成功")
    @ResponseBody
    @GetMapping(value = "/api/loginout")
    public Map<String, Object> doLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        session.removeAttribute("admin"); //消耗session
        Map<String, Object> map = new HashMap<String, Object>();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {//遍历所有Cookie
            if(cookies != null && cookies.length >0){//找到对应的cookie
                cookie.setMaxAge(0);//Cookie并不能根本意义上删除，只需要这样设置为0即可
                cookie.setPath("/");//很关键，设置成跟写入cookies一样的，全路径共享Cookie
                response.addCookie(cookie);//重新响应
                map.put("code","1");  //1表示注销成功
                map.put("msg","注销成功！");
                System.out.println("注销成功！");
            }
        }



            return map;

    }
}

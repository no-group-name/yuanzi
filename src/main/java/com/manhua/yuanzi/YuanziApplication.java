package com.manhua.yuanzi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.manhua.yuanzi.dao")//与dao层的接口@Mapper二选一写上即可(主要作用是扫包)，扫描的是mapper.xml中namespace指向值的包位置
@SpringBootApplication
public class YuanziApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(YuanziApplication.class, args);
    }

}

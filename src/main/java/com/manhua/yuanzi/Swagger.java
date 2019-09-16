package com.manhua.yuanzi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 通过@Configuration注解，让Spring来加载该类配置。再通过@EnableSwagger2注解来启用Swagger2。
 */
@EnableSwagger2
@Configuration
public class Swagger {




    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Spring boot Demo")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.manhua.yuanzi.web"))// 扫描的包所在位置
                .paths(PathSelectors.any()) // 扫描的 URL 规则
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("yuanzi manhua api", "localhost:8088/yuanzi/swagger-ui.html", "kiligqq@qq.com"); // 联系信息
        return new ApiInfoBuilder()
                .title("原子漫画API接口") // 大标题
                .description("原子漫画的API接口") // 描述
                .termsOfServiceUrl("localhost:8088/yuanzi/swagger-ui.htm")// 服务条款 URL
                .contact(contact)
                .version("1.0")// 版本
                .build();
    }
}

package com.tanbo.srb.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tanbo
 * @create 2021-10-18-10:45
 */
@Configuration
@EnableSwagger2
public class Swagger2Configration {

    @Bean
    public Docket AdminDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    private ApiInfo adminInfo(){

        return new ApiInfoBuilder().title("后台管理系统api")
                .contact(new Contact("谭波","http://localhost:8110/web/core","279166601@qq.com"))
                .build();
    }

    @Bean
    public Docket WebDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/web/.*")))
                .build();
    }

    private ApiInfo webInfo(){

        return new ApiInfoBuilder().title("前台api")
                .contact(new Contact("谭波","http://localhost:8110/admin/core","279166601@qq.com"))
                .build();
    }

    @Bean
    public Docket OSSDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ossApi")
                .apiInfo(ossInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/oss/.*")))
                .build();
    }

    private ApiInfo ossInfo(){

        return new ApiInfoBuilder().title("OSSapi")
                .contact(new Contact("谭波","http://localhost:8110/admin/core","279166601@qq.com"))
                .build();
    }
}

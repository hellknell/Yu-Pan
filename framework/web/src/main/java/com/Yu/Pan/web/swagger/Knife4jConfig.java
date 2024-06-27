package com.Yu.Pan.web.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;

/**
 * @ClassName: SwaggerConfig
 * @Description:
 * @Author: dangbo
 * @Date: 2021/3/31 14:13
 * @Version
 */
@Configuration
@EnableSwagger2WebMvc
// @EnableKnife4j    // 因为在配置文件中配置，因此不需要这个注解了
public class Knife4jConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Docket docket() {
        // 设置显示的swagger环境信息
        // 判断是否处在自己设定的环境当中

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组名称")  // 配置api文档的分组
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Yu.Pan")) //配置扫描路径
                .paths(PathSelectors.any()).build(); // 配置过滤哪些

    }
    // api基本信息
    private ApiInfo apiInfo() {
        return new ApiInfo("Yu-Pan项目",
                "测试swagger-ui",
                "v1.0",
                "http://mail.qq.com",
                new Contact("dangbo", "http://mail.qq.com", "145xxxxx@qq.com"),  //作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}

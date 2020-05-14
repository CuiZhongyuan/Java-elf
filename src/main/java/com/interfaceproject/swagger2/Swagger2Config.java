package com.interfaceproject.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/***
 * @ClassName: swagger2注册类
 * @Description: swagger2配置文件
 * @Auther: czy
 * @Date: 15:03 2020/05/09
 * @version : V1.0.0
 * swagger2 登录地址：http://ip:port/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                //特别注意这里是项目的控制层路径，如果不对会出现：No operations defined in spec!
//                .apis(RequestHandlerSelectors.basePackage("com.interfaceproject.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public Docket createRestApi() {

        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("header").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数配置end
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.interfaceproject.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);//注意这里
    }


    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("博客", "https://www.jianshu.com/u/acf637a73f52", "czy725@yeat.net"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

}
package com.ace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableAsync
public class XwApplication {

    public static void main(String[] args) {
        SpringApplication.run(XwApplication.class, args);
    }

    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XW")
                .description("XW")
                .version("1.0")
                .build();
    }

    @Bean
    Docket blsApiGatewayApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("XW")
                .apiInfo(apiInfo())
                //.select()
                //.paths(PathSelectors.regex("/*"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ace"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .enableUrlTemplating(true)
                .forCodeGeneration(true);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}

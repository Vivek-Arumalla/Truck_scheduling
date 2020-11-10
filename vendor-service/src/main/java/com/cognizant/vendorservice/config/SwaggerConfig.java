package com.cognizant.vendorservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("vendor-Service")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cognizant.vendorservice.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo("Vendor-Service","Customers can add, update, delete and search vendors.","1.0","",new Contact("Vivek","","vivek@gamil.com"),"Apache License","", Arrays.asList());
    }

}

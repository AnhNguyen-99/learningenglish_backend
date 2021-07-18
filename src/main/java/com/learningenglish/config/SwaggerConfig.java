package com.learningenglish.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configurable
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization")                 // name of header
                .modelRef(new ModelRef("string"))
                .parameterType("header")               // type - header
                .defaultValue("Basic em9uZTpteXBhc3N3b3Jk")        // based64 of - zone:mypassword
                .required(true)                // for compulsory
                .build();
        java.util.List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .globalOperationParameters(aParameters);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("TITLE",
                "DESCIPRION",
                "VERSION",
                "TERMS OF SERVICE URL",
                new Contact("NAME", "URL", "EMAIL"),
                "LICENSE", "LICENSE URL", Collections.emptyList());
    }
}

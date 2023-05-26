package org.serratec.sales_manager_grupo5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Pageable.class, Sort.class) // não quero essas classes na documentação
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.serratec.sales_manager_grupo5"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Gerenciador de vendas do Grupo 5")
                .description(
                        "API REST Gerenciador de vendas para projeto final da disciplina de Desenvolvimento de API Restful (Grupo 5 - Turma 04)")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/license/LICENSE-2.0")
                .version("1.0.0")
                .contact(new Contact("Serratec", "www.serrtatec.org.br", "teste@gmail.com"))
                .build();
        return apiInfo;
    }

}

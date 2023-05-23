package org.serratec.sales_manager_grupo5.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    private ModelMapper mapper() {
        return new ModelMapper();
    }

}

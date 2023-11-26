package com.company.bean_annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * · Stereotype annotations (@Component, @ComponentScan) cannot be used with classes that are not ours and cannot be changed.
 *   For ex: We can't edit (add @Component annotation) String, Integer, etc. classes, so we always follow the @Bean annotation with these classes.
 */
@Configuration
public class ConfigAny {

    @Bean
    String str(){
        return "Developer";
    }

    @Bean
    Integer number(){
        return 100;
    }
}

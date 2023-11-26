package com.company.bean_annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
 * · @Bean(name = "p1") can be used to name the beans in the @Configuration annotated classes.
 * · @Primary is a Default choice of the Spring. Spring will choose primary if there are multiple bean options, and we do
 *   not need to specify a name.
 */
@Configuration
public class ConfigApp {

    @Bean
    FullTimeMentor fullTimeMentor(){ // will return instance of the FullTimeMentor class
        return new FullTimeMentor();
    }

    //    @Bean(name = "p1")
    @Bean
    @Primary
    PartTimeMentor partTimeMentor(){
        return new PartTimeMentor();
    }

    //    @Bean(name = "p2")
    @Bean
    PartTimeMentor partTimeMentor2(){
        return new PartTimeMentor();
    }

}

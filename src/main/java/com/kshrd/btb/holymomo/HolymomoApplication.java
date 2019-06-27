package com.kshrd.btb.holymomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity
public class HolymomoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolymomoApplication.class, args);
//        String[] names = context.getBeanDefinitionNames();
//        for(int i=0;i<names.length;i++){
//            System.out.println(names[i]);
//        }
    }

}

package com.hp.javassist.auto;

import com.hp.javassist.javassistDemo.HpJavassist;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/28 21:57
 */
@Configuration
public class HpJavassistAutoConfigration {


    @Bean
    public ApplicationRunner applicationRunner(){
        return args -> {
            HpJavassist.buildClass();
            HpJavassist.addMethod();
        };
    }
}

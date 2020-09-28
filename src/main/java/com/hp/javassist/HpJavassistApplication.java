package com.hp.javassist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/28 21:50
 */
@SpringBootApplication(scanBasePackages = "com.hp.javassist")
public class HpJavassistApplication {
    public static void main(String[] args) {
        SpringApplication.run(HpJavassistApplication.class,args);
    }
}

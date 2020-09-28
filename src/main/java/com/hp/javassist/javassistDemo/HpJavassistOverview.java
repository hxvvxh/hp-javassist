package com.hp.javassist.javassistDemo;

import org.springframework.stereotype.Component;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/28 21:53
 */
@Component
public class HpJavassistOverview{
    private static final String SAY="say";

    public HpJavassistOverview() {
    }

    public String change(){
        return "Before";
    }
}

package com.hp.javassist;

import com.hp.javassist.javassistDemo.HpJavassist;
import com.hp.javassist.javassistDemo.HpJavassistOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/28 22:19
 */
@RestController
@ResponseBody
public class HpController {

    @Autowired
    private HpJavassistOverview overview;

    @GetMapping("/before")
    public String changeBefore(){
        return overview.change();
    }

    @GetMapping("/after")
    public String changAfter(){
        HpJavassist.addMethod();
        return overview.change();
    }

}

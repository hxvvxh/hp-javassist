package com.hp.javassist.translator;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.Translator;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/28 22:35
 */
public class HpTranslator implements Translator {
    @Override
    public void start(ClassPool classPool) throws NotFoundException, CannotCompileException {
        System.out.println("start [] classPool:  "+classPool);
    }

    @Override
    public void onLoad(ClassPool classPool, String s) throws NotFoundException, CannotCompileException {
        System.out.println("ClassPool[]  classPool:  "+classPool);
        System.out.println("ClassPool[]  s:   "+classPool);
    }
}

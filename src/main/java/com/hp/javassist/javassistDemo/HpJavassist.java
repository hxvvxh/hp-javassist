package com.hp.javassist.javassistDemo;

import javassist.*;

import java.lang.reflect.Method;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/28 21:52
 */
public class HpJavassist {

    public static void main(String[] args) {
        buildClass();
        addMethod();
    }

    public static void buildClass(){

        try {
            ClassPool pool=ClassPool.getDefault();

            //设置监听器
//            Translator translator=new HpTranslator();
//            Loader cl = new Loader();
//            cl.addTranslator(pool, translator);
//            cl.run("MyApp", args);

            //创建一个类
            CtClass ctClass=pool.makeClass("HpJavassist$Adapt");
            //创建一个属性
            CtField firstParam= null;
            firstParam = new CtField(pool.get("java.lang.String"),"name",ctClass);

            //设置属性的访问权限
            firstParam.setModifiers(Modifier.PRIVATE);

            //设置属性的默认值:
            ctClass.addField(firstParam,CtField.Initializer.constant("ceshi"));

            //设置get set 方法
            ctClass.addMethod(CtNewMethod.setter("setName",firstParam));
            ctClass.addMethod(CtNewMethod.getter("getName",firstParam));


            //添加无参的构造函数
            CtConstructor noConstructor=new CtConstructor(new CtClass[]{},ctClass);
            noConstructor.setBody("{name = \"test\";}");

            ctClass.addConstructor(noConstructor);

            // 添加有参的构造函数
            CtConstructor hasConstructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, ctClass);
            // $0=this / $1,$2,$3... 代表方法参数
            hasConstructor.setBody("{$0.name = $1;}");
            ctClass.addConstructor(hasConstructor);

            //添加一个方法
            // 创建一个名为printName方法，无参数，无返回值，输出name值
            CtMethod ctMethod = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, ctClass);
            ctMethod.setModifiers(Modifier.PUBLIC);
            ctMethod.setBody("{System.out.println(name);}");
            ctClass.addMethod(ctMethod);

            //写入文件
            String path=HpJavassist.class.getResource("/").getPath();
            ctClass.defrost();
            ctClass.setSuperclass(ctClass);
            ctClass.writeFile(path+"com/hp/javassist/javassistDemo");
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void addMethod(){

        try {
            ClassPool pool=ClassPool.getDefault();
            //获取JVM 中已经加载的类
            CtClass ctClass=pool.get(HpJavassist.class.getPackage().getName()+".HpJavassistOverview");
            //构造一个方法
            CtMethod ctMethod = new CtMethod(CtClass.voidType, "printSay", new CtClass[]{}, ctClass);
            ctMethod.setModifiers(Modifier.PUBLIC);
            ctMethod.setBody("{System.out.println(SAY);}");
            //添加方法
            ctClass.addMethod(ctMethod);

            //(1)直接实例化方法，不重写入class文件
            HpJavassistOverview overview= (HpJavassistOverview) ctClass.toClass().newInstance();
            Method method=overview.getClass().getMethod("printSay");
            method.invoke(overview);
            //(2)直接重写入class文件
            String path=HpJavassist.class.getResource("/").getPath();
//            //writeFile之后 此class文件会被冻结 不允许被修改
            ctClass.writeFile(path);

            //设置可以再次修改
            ctClass.defrost();
            //(3)修改原class 方法
            CtMethod change=ctClass.getDeclaredMethod("change");

            //
//            change.instrument(new ExprEditor(){
//                @Override
//                public void edit(MethodCall m) throws CannotCompileException {
//                    if (m.getMethodName().equals("change")){
//                        m.replace("{ $_ = \"After\"; }");
//                    }
//                }
//            });

            change.setModifiers(Modifier.PUBLIC);
            change.setBody("return \"After\";");
            String pathChange=HpJavassist.class.getResource("/").getPath();
            ctClass.writeFile(pathChange);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

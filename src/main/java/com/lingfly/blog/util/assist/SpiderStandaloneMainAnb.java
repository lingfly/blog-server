package com.lingfly.blog.util.assist;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class SpiderStandaloneMainAnb {
    public static void main(String[] args) throws Exception {
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spider-base-service.xml");
//        System.out.println("---" + context.getApplicationName());

        Class<?> aClass = Class.forName("com.lingfly.blog.util.assist.Standard");
        Method method = aClass.getMethods()[0];
        Parameter[] parameters = method.getParameters();
        System.out.println(parameters[0].getName());


//        Standard standard = new Standard();
//        standard.doSomething();
//        ClassPool pool = ClassPool.getDefault();
//        try {
//            CtClass clazz = pool.get("com.lingfly.blog.util.assist.Standard");
//            CtMethod cm = clazz.getDeclaredMethod("doSomething");
//            cm.insertAt(1,"{System.out.println(\"hello HotSwapper.\");}");  // clazz完全可以是全新的，这里只是为了测试方便而已
//
//            standard.doSomething();
//        } catch (CannotCompileException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (NotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
}

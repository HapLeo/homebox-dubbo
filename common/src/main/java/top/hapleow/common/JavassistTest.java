package top.hapleow.common;

import javassist.*;

import java.lang.reflect.Method;

public class JavassistTest {

    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        CtClass clazz = classPool.makeClass("top.hapleow.user.UserServiceImpl");
        StringBuffer body = null;
        CtField ctField = new CtField(classPool.get("java.lang.String"),"username",clazz);
        ctField.setModifiers(Modifier.PRIVATE);
        clazz.addField(ctField);
        clazz.addMethod(CtNewMethod.setter("setUsername",ctField));

        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, clazz);
        clazz.addConstructor(ctConstructor);
        clazz.writeFile("/Users/xxx/");
        Class aClass = clazz.toClass();
        Object o = aClass.newInstance();
        Method method = o.getClass().getMethod("execute", new Class[]{});
        method.invoke(o,new Object[]{});

    }
}

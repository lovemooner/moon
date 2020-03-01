package love.moon.javassit;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

/**
 * Author: lovemooner
 * Date: 2017/5/8 17:50
 */
public class JavassitTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException{
        CtClass ctClass=ClassPool.getDefault().get("love.celery.javassit.Emp");
        String oldName="printInfo";
        CtMethod ctMethod=ctClass.getDeclaredMethod(oldName);
        String newName=oldName+"$impl";
        ctMethod.setName(newName);
        CtMethod newMethod=CtNewMethod.copy(ctMethod,"printInfo",ctClass, null);
        StringBuffer sb=new StringBuffer();
        sb.append("{System.out.println(\"22222222\");\n")
                .append(newName+"($$);\n")
                .append("System.out.println(\"11111111111\");\n}");
        System.out.println(sb.toString());
        newMethod.setBody(sb.toString());
        //增加新方法
        ctClass.addMethod(newMethod);
        //类已经更改，注意不能使用A a=new A();，因为在同一个classloader中，不允许装载同一个类两次
        Emp a=(Emp)ctClass.toClass().newInstance();
        a.printInfo();
    }
}
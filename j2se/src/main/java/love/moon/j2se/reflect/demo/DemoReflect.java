package love.moon.j2se.reflect.demo;

import javax.persistence.Entity;
import java.lang.reflect.*;
import java.util.Map;

/**
 * Author: lovemooner
 * Date: 2017/5/8 14:07
 */
@Entity(name = "DemoReflect")
public class DemoReflect implements IDemoReflect {

    private String name;

    public DemoReflect() {
    }

    public DemoReflect(String name) {
        this.name = name;
    }

    public void setName(String name) {
        System.out.println("SetName Param name" + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 处理泛型
     */
    public static void analyzeMethod(Class clazz) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {


        Object obj = clazz.newInstance();
        Method setName = clazz.getDeclaredMethod("setName", String.class);
        setName.invoke(obj, "设置name！");

        Method getName = clazz.getDeclaredMethod("getName");
        String msg = (String) getName.invoke(obj);
        System.out.println(msg);

        Method showParameter = clazz.getDeclaredMethod("showParameter", Map.class);
        Type[] paramTypeList = showParameter.getGenericParameterTypes();// 方法的参数列表
        System.out.print("showParameter Param List==== ");
        for (Type paramType : paramTypeList) {
            System.out.print("  " + paramType);// 参数类型
            if (paramType instanceof ParameterizedType)/**//* 如果是泛型类型 */ {
                Type[] types = ((ParameterizedType) paramType)
                        .getActualTypeArguments();// 泛型类型列表
                System.out.print("  TypeArgument: ");
                for (Type type : types) {
                    System.out.print("   " + type);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DemoReflect demo = new DemoReflect();
        demo.setName("aa");

        Class clazz = DemoReflect.class;
//      Interfaces
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class inf : interfaces) {
            Class<?> interfaceClass = Class.forName(inf.getName(), false, inf.getClassLoader());
            System.out.println(interfaceClass.isInterface());
            System.out.println(" " + inf.getName());

        }
//      Constructor
        Constructor constructor = DemoReflect.class.getConstructor(String.class);
        DemoReflect demoReflect = (DemoReflect) constructor.newInstance("constructor");
        System.out.println(demoReflect.getName());
//        field
        Field field = clazz.getDeclaredField("name");
//        System.out.println("Reflect -> " + field.getChar(demoReflect)); //获取域的值
        analyzeMethod(clazz);


    }


    private static Map<String, Double> showParameter(Map<String, Object> map) {
        return null;
    }

    @Override
    public void analyzeClass() {

    }
}

package love.moon.j2se.reflect.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: lovemooner
 * Date: 2018/6/7 14:00
 */
public class ClassReLoader extends ClassLoader {
    private String classPath;

    public ClassReLoader(String classpath) {
        this.classPath = classpath;
    }

    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] classData = getData(className);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass("Sample", classData, 0, classData.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + className;
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        try {
//            String path = "D:\\project\\moon\\milk\\target\\classes\\love\\moon\\reflect\\";
//            ClassReLoader reLoader = new ClassReLoader(path);
//            Class r = reLoader.findClass("Sample.class");
//            System.out.println(r.newInstance());
//            Class r2 = reLoader.findClass("Sample.class");
//            System.out.println(r2.newInstance());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
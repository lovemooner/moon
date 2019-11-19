package love.moon.j2se.reflect.classloader;

import love.moon.j2se.reflect.Sample;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: lovemooner
 * Date: 2017/5/4 16:45
 */
public class PathClassLoader extends ClassLoader {
    private String classPath;

    public PathClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
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

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String path = "D:\\object\\love\\moon\\reflect\\";
        ClassLoader pcl = new PathClassLoader(path);
        Class c = pcl.loadClass("love.moon.j2se.reflect.Sample");
        Sample sample= (Sample) c.newInstance();
        System.out.println(sample.sayHello());
    }
}
package love.moon.reflect.classloader;

import love.moon.reflect.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Launcher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Author: lovemooner
 * Date: 2017/5/4 16:45
 */
public class MyClassLoader extends ClassLoader {
    private static final Logger LOG = LoggerFactory.getLogger(MyClassLoader.class);

    private String name;// 类加载器的名字，方便看测试结果
    private String path = "d:\\";
    private final String fileType = ".class";

    public MyClassLoader(String name) {
        super();
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileType() {
        return fileType;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        LOG.info("findClass name:{}", name);
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try {
            name = name.replace(".", "\\");
            is = new FileInputStream(new File(path + name + fileType));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void showClassLoader(ClassLoader loader) throws Exception {
        System.out.println();
        Class clazz = loader.loadClass("love.moon.reflect.Sample");
        System.out.println(clazz.getClassLoader());
        Sample sample =(Sample)clazz.newInstance();
        System.out.println(sample.sayHello());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(        Thread.currentThread().getContextClassLoader());
        String path = "D:\\object\\";
        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath(path);
        showClassLoader(loader1);
//        MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
//        loader2.setPath(path);
//        showClassLoader(loader2);
//        MyClassLoader loader3 = new MyClassLoader(null, "loader3");
//        loader3.setPath(path);
//        showClassLoader(loader3);
    }
}
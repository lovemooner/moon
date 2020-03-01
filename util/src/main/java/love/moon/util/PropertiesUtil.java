package love.moon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

    private static Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

    public static boolean saveToFile(String filePath, Map<String, String> map) {
        File file = new File(filePath);
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Properties props = new Properties();

        for (String key : map.keySet()) {
            props.setProperty(key, map.get(key).toString());
        }
        try {
            props.store(out, "save map");
            return true;
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
            return false;
        }
    }


    public static Properties load(String path) throws IOException {
        File file = new File(path);
        Properties properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(file),
                    "UTF8"));
        return properties;
    }

    public static Properties loadFromClassPath(String path) throws IOException {
        Properties properties = new Properties();
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(path),
                    "UTF8"));
        return properties;
    }

}

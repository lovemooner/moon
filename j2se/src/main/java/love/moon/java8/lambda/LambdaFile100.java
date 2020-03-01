package love.moon.java8.lambda;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LambdaFile100 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Files.find(Paths.get(ClassLoader.getSystemResource("a.txt").toURI()), Integer.MAX_VALUE,
                (filePath, fileAttr) -> fileAttr.isRegularFile())
                .forEach(file -> {
                    System.out.println(file.getFileName());
                });
    }
}

package love.moon.io;

import love.util.FileUtil;
import love.util.NumberUtil;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;

/**
 * User: lovemooner
 * Date: 17-3-28
 * Time: 上午10:22
 */
public class RandomAccessFileDemo {

    public void testRandomAccessFile() throws IOException {
        int blockNumber = 3;
        //创建文件
        String path = "c:\\test\\";
        String uuid = UUID.randomUUID().toString();
        File videoPath = new File(path + uuid);
        videoPath.mkdir();

        //1---------------------------------分割视频 ------------------------------------
        String srcFilePath = path + "test.mp4";
        File srcFile = new File(srcFilePath);
        long len = srcFile.length();
        long blockLen = 1024 * 1024;
        long start = 0;
        for (int i = 0; i < blockNumber; i++) {
            long offset = blockLen;
            if (start >= len) {
                break;
            }
            if (i == (blockNumber - 1)) {
                offset = srcFile.length() - start;
            }
            File tempFile = new File(videoPath.getPath() + FileUtil.getOSDisk() + i);
            tempFile.createNewFile();
            OutputStream out = new FileOutputStream(tempFile);
            RandomAccessFile fileReader = null;
            try {
                byte[] tBytes = new byte[NumberUtil.intValue(offset)];
                fileReader = new RandomAccessFile(srcFile, "r");
                fileReader.seek(start);
                fileReader.read(tBytes, 0, NumberUtil.intValue(offset));
                // 输出表头
                out.write(tBytes, 0, tBytes.length);
            } finally {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            start += offset;
        }

        //2---------------------------------组装视频 ------------------------------------
        //排序文件块
        String[] blockFiles = videoPath.list();
        Arrays.sort(blockFiles, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return NumberUtil.subtract(o1, o2).intValue();
            }
        });
        //创建视频文件
        File videoFile = new File(videoPath.getPath() + FileUtil.getOSDisk() + uuid + ".mp4");
        videoFile.createNewFile();
        //组装文件
        RandomAccessFile fileWrite = null;
        RandomAccessFile fileReader = null;
        try {
            long alreadyWrite = 0;
            int fLen = 0;
            byte[] buf = new byte[1024 * 10];
            fileWrite = new RandomAccessFile(videoFile, "rw");
            for (int i = 0; i < blockFiles.length; i++) {
                fileWrite.seek(alreadyWrite);
                File tmpFile = new File(videoPath + FileUtil.getOSDisk() + blockFiles[i]);
                fileReader = new RandomAccessFile(tmpFile, "rw");
                // 写入
                while ((fLen = fileReader.read(buf)) != -1) {
                    fileWrite.write(buf, 0, fLen);
                }
                alreadyWrite += tmpFile.length();
            }
        } finally {
            if (fileWrite != null) {
                fileWrite.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
        }


    }
}

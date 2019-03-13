package love.moon.net;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class HttpServer100 {
    Random random = new Random();
    static ConcurrentHashMap map = new ConcurrentHashMap();
    int port = 7878;

    public static void main(String[] args) throws IOException {
        HttpServer100 server = new HttpServer100();
        server.start();
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server is running on port:" + serverSocket.getLocalPort());
        while (true) {
            final Socket socket = serverSocket.accept();
//                map.put(socket.hashCode(),socket);
            System.out.println("build a new tcp link with client,the cient address:" +
                    socket.getInetAddress() + ":" + socket.getPort());
            new Thread(() -> {
                try {
                    service(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    public void service(Socket socket) throws Exception {
        //read http information
        InputStream inputStream = socket.getInputStream();
        int count = inputStream.available();
        if (count == 0) {
            System.out.println("error.....");
            StringBuilder sb = new StringBuilder();
            sb.append("HTTP/1.1 200 OK\r\n");
            sb.append("Content-Type:text/html" + "\r\n");
            sb.append("Content-Length:0" + "\r\n");
            sb.append("\r\n");
            OutputStream out = socket.getOutputStream();
            out.write(sb.toString().getBytes());
            socket.close();
            return;
        }
        byte[] buffer = new byte[count];
        int readCount = 0; // 已经成功读取的字节的个数
        while (readCount < count) {
            readCount += inputStream.read(buffer, readCount, count - readCount);
        }

        String requestStr = new String(buffer);
        System.out.println("print http head-----------------------------");
        System.out.println(requestStr);
        //mime
        String contentType;
        if (requestStr.indexOf("html") != -1 || requestStr.indexOf("htm") != -1)
            contentType = "text/html";
        else {
            contentType = "application/octet-stream";
        }
        contentType = "text/html";
        String content = "hello " + random.nextInt(1000);
        //http response head
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type:" + contentType + "\r\n");
        sb.append("Content-Length:" + content.length() + "\r\n");
        sb.append("\r\n");
        OutputStream out = socket.getOutputStream();
        out.write(sb.toString().getBytes());
//         entity body
        out.write(content.getBytes(), 0, content.length());
//        Thread.sleep(1000);
        //close tcp link
        socket.close();
    }

}

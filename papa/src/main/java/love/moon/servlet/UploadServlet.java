package love.moon.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.List;


public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * 上传目录名
     */
    private static final String UPLOAD_DIR = "uploadDir/img/";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        up2(request,response);
    }


    public void up(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Enumeration<String> reqHeadInfos = request.getHeaderNames();
        while (reqHeadInfos.hasMoreElements()) {
            String headName = (String) reqHeadInfos.nextElement();
            String headValue = request.getHeader(headName);//根据请求头的名字获取对应的请求头的值
            System.out.println(headName+":"+headValue);
        }
        System.out.println(request.getParameter("uploaderName"));


        /****** 初始化部分 ******/
        //设置编码格式，前端后台统一是utf-8
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");

        /****** 文件路径部分 用的是项目发布的相对路径******/
        // Servlet上下文对象
        ServletContext servletContext = this.getServletConfig().getServletContext();
        // tomcat的项目路径，记住要加斜杠
        String fileName = "test.jpg";//保存为什么名字
        String realPath = servletContext.getRealPath(UPLOAD_DIR) + "/";//保存的路径
        String filePath = realPath + fileName;//合起来就是完整的文件路径了
        System.out.println("UploadTestServlet filePath:" + filePath);
        File realPathFile = new File(realPath);
        if (!realPathFile.exists()) {
            realPathFile.mkdirs();
        }

        /****** 读写部分  ******/
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        //传给页面的输出流
        ServletInputStream sis = request.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        ;
        while ((len = sis.read(b)) != -1) {
            fos.write(b, 0, len);
        }

        /****** 关闭资源  ******/
        fos.close();
        sis.close();

        /****** 把图片地址，转发回页面 ******/
        try {
            request.setAttribute("path", UPLOAD_DIR + fileName);
            System.out.println("UploadTestServlet path:" + UPLOAD_DIR + fileName);
            request.getRequestDispatcher("/test/UploadTest.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print("Done");
    }

    public void up2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        File file = new File(UPLOAD_DIR);
        // 判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(UPLOAD_DIR + "目录不存在，需要创建");
            // 创建目录
            file.mkdir();
        }
        // 返回值提示
        String message = "";
        // 创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        try {
            // 创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            if (!ServletFileUpload.isMultipartContent(request)) {
                // 按照传统方式获取数据
                return;
            }
            // 使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                // 如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    // 转码
                    String value = item.getString("UTF-8");
                    value = new String(value.getBytes("iso-8859-1"), "UTF-8");
                    System.out.println(name + "=" + value);
                } else {
                    // 如果fileitem中封装的是上传文件
                    //得到文件名
                    String filename = item.getName();
                    System.out.println("filename=" + filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建缓冲区
                    byte buffer[] = new byte[1024];
                    //创建输出流对象，用于将缓冲区的数据读出到保存路径
                    FileOutputStream output = new FileOutputStream(UPLOAD_DIR + "\\" + filename);
                    //判断输入流中的数据是否已经读完
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示输入流中还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        output.write(buffer, 0, len);
                    }
                    in.close();
                    output.close();
                    message = "success";
                }
            }
        } catch (FileUploadException e) {
            message = "failure";
            e.printStackTrace();
        }
        out.print(message);
    }


}

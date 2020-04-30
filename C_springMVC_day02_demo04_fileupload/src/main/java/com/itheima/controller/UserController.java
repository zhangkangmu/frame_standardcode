package com.itheima.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/19 9:58
 * @Version V1.0
 */
@Controller  // 该注解表示该类控制器
@RequestMapping(value = "/user") // 一级路径
public class UserController {

    @RequestMapping(value = "/testFileUpload")
    public String testFileUpload(){
        System.out.println("欢迎访问UserController类中的testFileUpload的方法！");
        return "success";
    }

    // 普通方式（Servlet）完成文件上传
    @RequestMapping(value = "/fileUpload")
    public String fileUpload(HttpServletRequest request) throws Exception {
        System.out.println("欢迎访问UserController类中的fileUpload的方法！");
        // 1：上传的位置，获取服务器地址下的项目路径，将上传文件放置到项目路径下的uploads文件夹
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(path);
        // 如果uploads文件夹不存在，需要创建文件夹
        if(!file.exists()){
            file.mkdirs();
        }
        // 2：创建ServletFileUpload，完成文件上传
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        // 3：解析Request，遍历FileItem对象
        List<FileItem> list = servletFileUpload.parseRequest(request);
        // 遍历list，解析每个文件项
        if(list!=null && list.size()>0){
            for (FileItem fileItem : list) {
                // 普通表单
                if(fileItem.isFormField()){

                }
                // 文件
                else{
                    // 4：上传的文件名
                    String fileName = fileItem.getName(); // 01.jpg
                    String uuid = UUID.randomUUID().toString().toUpperCase().replace("-","");
                    fileName = uuid+"_"+fileName;
                    // 5：上传文件
                    fileItem.write(new File(file,fileName));
                    // 删除缓冲区中文件
                    fileItem.delete();
                }
            }
        }
        return "success";
    }

    // SpringMVC的上传组件（底层Servlet）完成文件上传
    // 1：接收MultipartFile类型的文件
    @RequestMapping(value = "/fileUpload2")   //主要MultipartFile的变量名要与前端传来的file文件类型的name名称一致
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("欢迎访问UserController类中的fileUpload2的方法！");
        // 2：上传的位置，获取服务器地址下的项目路径，将上传文件放置到项目路径下的uploads文件夹
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(path);
        // 如果uploads文件夹不存在，需要创建文件夹
        if (!file.exists()) {
            file.mkdirs();  //mkdirs:创建由此File表示的目录，包括任何必需但不存在的父目录。  mkdir:创建由此File表示的目录。
        }

        // 3：上传的文件名
        String fileName = upload.getOriginalFilename(); // 01.jpg
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + "_" + fileName;
        // 4：上传文件
        upload.transferTo(new File(file, fileName));
        return "success";
    }

    // 跨服务器的文件上传
    // 1：接收MultipartFile类型的文件
    @RequestMapping(value = "/fileUpload3")
    public String fileUpload3(Model model, MultipartFile upload) throws Exception {
        System.out.println("欢迎访问UserController类中的fileUpload3的方法！");
        // 2：上传的位置，指定另一台服务器部署的url  注意:这里一定要有斜杠
        String path = "http://localhost:8082/springmvc_day02_fileuploadServer/uploads/";

        // 3：上传的文件名
        String fileName = upload.getOriginalFilename(); // 01.jpg
            String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + "_" + fileName;
        // 4：上传文件  注意:这里是create,不是new
        Client client = Client.create();
        WebResource resource = client.resource(path + fileName);
        resource.put(upload.getBytes());
        // 传递文件名
        model.addAttribute("fileName",fileName);
        return "success";
    }
}

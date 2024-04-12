package com.snake.springbootlilproject.controller;

import com.snake.springbootlilproject.pojo.Result;
import com.snake.springbootlilproject.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    //注入阿里云
    @Autowired
    private AliOSSUtils aliOSSUtils;

    /*
    *
    * 本地存储文件
    * */
  /*  @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传:{},{},{}",username,age,image);
         //获取原始文件名
        String originalFilename = image.getOriginalFilename();
        //唯一文件名(不能重复) --uuid （通用唯一识别码）
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新文件面：{}",newFileName);
        //接收文件存储在服务器磁盘目录
        image.transferTo(new File("D:\\snakexxx1\\中国梦.txt"+originalFilename));
        return Result.success();
    }*/



    /*
    * 阿里云oss
    * */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：{}",image.getOriginalFilename());
        //调用阿里云oss工具类进行文件上传
        String url = aliOSSUtils.upload(image);//调用阿里云oss工具类，将上传的文件存入阿里云
        log.info("文件上传完成，文件访问的url：{}",url);//将图片上传完成后的url返回，用于浏览器回显展示
        return Result.success(url);
    }
}

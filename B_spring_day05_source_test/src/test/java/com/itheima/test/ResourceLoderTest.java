/**
 * Copyright (c) 2020 itheima.com, All rights reserved.
 *
 * @Author: lvyang
 */
package com.itheima.test;

import org.junit.Test;
import org.springframework.core.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2020年06月17日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class ResourceLoderTest {

    //通过inputStream读取文件中的内容并输出到控制台
    public void soutInputStream(InputStream inputStream){
        BufferedReader reader = null;
        String tempString = null;
        try {
            int line =1;
            reader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            while ((tempString = reader.readLine()) != null) {
                System.out.println(tempString);
                line ++ ;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testn(){
        //resources下
        String url = "test.txt";
        //e盘下
        String url2 = "file:E:/test.txt";
        //url下,可以使用nginx代理一个路径
        String url3 = "http://localhost/test.txt";
        try {
          /*  ClassPathResource classPathResource = new ClassPathResource(url);
            soutInputStream(classPathResource.getInputStream());
            FileUrlResource fileUrlResource = new FileUrlResource(url2);
            soutInputStream(fileUrlResource.getInputStream());
            UrlResource urlResource = new UrlResource(url3);
            soutInputStream(urlResource.getInputStream());*/
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            soutInputStream(resourceLoader.getResource(url).getInputStream());
            soutInputStream(resourceLoader.getResource(url2).getInputStream());
            soutInputStream(resourceLoader.getResource(url3).getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
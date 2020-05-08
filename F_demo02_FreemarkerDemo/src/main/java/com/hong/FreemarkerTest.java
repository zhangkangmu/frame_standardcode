package com.hong;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * freemarker入门案例
 */
public class FreemarkerTest {

    public static void main(String[] args) throws IOException, TemplateException {
        // 第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File("C:\\working\\workspace\\javaee-dev87\\FreemarkerDemo\\src\\main\\resources"));
        // 第三步：设置模板文件使用的字符集。一般就是 utf-8。
        configuration.setDefaultEncoding("utf-8");
        // 第四步：加载一个模板，创建一个模板对象。
        Template template = configuration.getTemplate("test.ftl");
        // 第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。
        Map map = new HashMap();
        map.put("name","老王");
        map.put("message","欢迎来到直播间");
        //为success赋值
        map.put("success",false);

        List goodsList = new ArrayList();
        Map goods1 = new HashMap();
        goods1.put("name","苹果笔记本");
        goods1.put("price",15000);

        Map goods2 = new HashMap();
        goods2.put("name","联想笔记本");
        goods2.put("price",9000);

        Map goods3 = new HashMap();
        goods3.put("name","神州笔记本");
        goods3.put("price",4000);

        Map goods4 = new HashMap();
        goods4.put("name","华为笔记本");
        goods4.put("price",8000);

        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        goodsList.add(goods4);

        map.put("goodsList",goodsList);
        // 第六步：创建一个 Writer 对象，一般创建 FileWriter 对象，指定生成的文件名。
        Writer writer = new FileWriter(new File("C:\\working\\workspace\\javaee-dev87\\FreemarkerDemo\\src\\main\\resources\\test.html"));
        // 第七步：调用模板对象的 process 方法输出文件。
        template.process(map,writer);
        // 第八步：关闭流。
        writer.close();

    }
}

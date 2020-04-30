package com.itheima.factory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BeanFactroy
 * @Description TODO

 * @Date 2020/3/14 11:19
 * @Version V1.0
 * spring的ioc实现原理
 */
public class BeanFactroy {

    // 通过Dom4j解析applicationContext.xml，将解析后的bean标签中的对象通过反射的方式创建，将创建的对象放置到Map容器中（目前为止2个）
    // map集合的key：bean中的id；map集合的value：Class.forName(bean中的class).newInstance()
    private static Map<String,Object> objMap = new HashMap<String,Object>();

    // 完成注入的时候，使用Dom4j读取property标签中的内容，返回List<Element>，使用Map集合将property表中的集合对象存放进去，下面完成注入
    private static Map<String,Object> elementsMap = new HashMap<String,Object>();

    // 调用BeanFactroy就会执行，而且只会执行一次（spring执行一次）
    static{
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // init方法，解析applicationContext.xml文件，将对象通过反射的方式放置到Map集合中
    public static void init() throws Exception{
        // 一：控制反转IOC（将创建的对象，放置到objMap集合中）
        // 1：解析XML
        SAXReader saxReader = new SAXReader();
        InputStream in = BeanFactroy.class.getClassLoader().getResourceAsStream("applicationContext.xml");
        Document document = saxReader.read(in);
        Element rootElement = document.getRootElement(); // 获取根（例如：beans）
        List<Element> elements = rootElement.elements("bean");// 获取beans节点中的bean节点，返回List<Element>
        // 2：遍历elements，获取bean中的id属性和class属性
        if(elements!=null && elements.size()>0){
            for (Element bean : elements) {
                String id = bean.attributeValue("id");
                String aClass = bean.attributeValue("class");
                Object obj = Class.forName(aClass).newInstance();
                // 3：存放到objMap集合中
                objMap.put(id,obj);
                // 4：完成依赖注入，解析property标签
                List<Element> propertyElements = bean.elements("property");
                if(propertyElements!=null && propertyElements.size()>0){
                    elementsMap.put(id,propertyElements);
                }
            }
        }
        // 二：依赖注入DI（将创建后的对象，通过propery属性，注入给另一个对象）
        if(elementsMap!=null && elementsMap.size()>0){
            // 1：遍历key值
            for (String id : elementsMap.keySet()) {
                // 通过id，获取对象（例如：AccountServiceImpl对象）
                Object obj = objMap.get(id);
                // 通过id，获取List<Element>，从而获取property中的标签内容（name和ref）
                List<Element> propertyElements = (List<Element>)elementsMap.get(id);
                if(propertyElements!=null && propertyElements.size()>0){
                    for (Element property : propertyElements) {
                        String name = property.attributeValue("name");  // dao
                        String ref = property.attributeValue("ref");    // accountDao
                        if(ref!=null && !ref.equals("")){
                            Object propertyObj = objMap.get(ref); // AccountDaoImpl对象
                            // 2：将propertyObj对象注入给obj对象中的dao属性
                            // 根据属性名称获取对应的属性
                            Field field = obj.getClass().getDeclaredField(name);  // 获取AccountServiceImpl类中的AccountDao dao;
                            // 让obj对象中的属性，可以被修改的（不添加报错can not access a member of class com.itheima.service.impl.AccountServiceImpl with modifiers ）
                            field.setAccessible(true);
                            // 将propertyObj对象注入给obj对象，通过Field字段完成
                            field.set(obj,propertyObj);
                        }
                    }
                }
            }
        }
    }

    // 通过bean的id名称，获取创建对象
    public static Object getBean(String beanname) {
        return objMap.get(beanname);
    }
}

package com.hong.controller;

import com.hong.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/19 9:58
 * @Version V1.0
 */
@Controller  // 该注解表示该类控制器
@RequestMapping(value = "/anno") // 一级路径
@SessionAttributes(names = {"username", "age"}, types = {Date.class})   //username和age的名称都可以共享，Date类型的数据也可以共享
// Session的数据共享，使用name表示按照名称存放Session，使用types表示按照类型存放Session
public class AnnoController {

    /**
     * 4.1 @RequestParam注解
     * 前端传递的变量和后端接收的变量名字不一致时,用注解@RequestParam来实现数据的传递
     * 属性：
     * value：请求参数中的名称。
     * required：请求参数中是否必须提供此参数。默认值：true。表示必须提供，如果不提供将报错。
     * defaultValue：表示默认值，如果不传递值
     * defaultValue一般可以用在分页场景，不传递当前页，不传递每页记录数的时候，可以使用defaultValue指定默认值（1,10）
     */
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam(value = "name", required = true) String username, @RequestParam(value = "age", defaultValue = "10") Integer age) {
        System.out.println("欢迎执行AnnoController类中的testRequestParam方法！username:" + username + "   age:" + age);
        return "success";
    }

    /**
     * 4.2 @RequestBody注解
     * required：是否必须有请求体。默认值是:true。
     * 当取值为true时,get请求方式会报错。(HTTP Status [400] – [Bad Request])
     * 如果取值为false，get请求得到是null。(body123:null)
     */
    @RequestMapping(value = "/testRequestBody")
    public String testRequestBody(@RequestBody(required = false) String body123) {
        // 传递默认：URLEncoder.encode("body123","UTF-8");
//        try {
//            body123 = URLDecoder.decode(body123, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        System.out.println("欢迎执行AnnoController类中的testRequestParam方法！body123:" + body123);
        return "success";
    }


    /**
     * 4.3 @PathVariable注解
     * url支持占位符是spring3.0之后加入的。是springmvc支持restful风格URL的一个重要标志。
     * * Rest请求的传递参数的格式：anno/testPathVariable/3/zhangsan
     */
    //有?的也可以直接获取
   // @RequestMapping(value = "/testPathVariable/{id}/{username}?pasword=allen")
   // public String testPathVariable(@PathVariable(value = "id") Integer id, @PathVariable(value = "username") String name,String pasword) {

        @RequestMapping(value = "/testPathVariable/{id}/{username}")
    public String testPathVariable(@PathVariable(value = "id") Integer id, @PathVariable(value = "username") String name) {
        System.out.println("欢迎执行AnnoController类中的testPathVariable方法！id:" + id + "    name:" + name);
        return "success";
    }

    /**
     * REST风格
     */
    @RequestMapping(value = "/testPathVariable", method = RequestMethod.POST)
    public String save(User user) {
        System.out.println("【新增POST】欢迎执行AnnoController类中的testPathVariable方法！user:" + user);
        return "success";
    }

    @RequestMapping(value = "/testPathVariable", method = RequestMethod.PUT)
    @ResponseBody // 让视图解析器失效，返回success，输出success
    public String update(User user) {
        System.out.println("【更新PUT】欢迎执行AnnoController类中的testPathVariable方法！user:" + user);
        return "success";
    }

    @RequestMapping(value = "/testPathVariable/{id}", method = RequestMethod.DELETE)
    @ResponseBody // 让视图解析器失效，返回success，输出success
    public String delete(@PathVariable(value = "id") Integer id) {
        System.out.println("【删除DELETE】欢迎执行AnnoController类中的testPathVariable方法！id:" + id);
        return "success";
    }

    @RequestMapping(value = "/testPathVariable/{id}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "id") Integer id) {
        System.out.println("【查询GET】欢迎执行AnnoController类中的testPathVariable方法！id:" + id);
        return "success";
    }

    /**
     * 4.4 @RequestHeader注解（了解）
     * 作用：
     * 用于获取请求消息头。
     * 属性：
     * value：提供消息头名称
     * required：是否必须有此消息头
     */
    @RequestMapping(value = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String head) {
        System.out.println("【查询GET】欢迎执行AnnoController类中的testRequestHeader方法！head:" + head);
        return "success";
    }

    /**
     * 4.5 @CookieValue 注解（了解）
     * 作用：
     * 用于把指定cookie名称的值传入控制器方法参数。
     * 属性：
     * value：指定cookie的名称。
     * required：是否必须有此cookie。
     */
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookievalue) {
        System.out.println("欢迎执行AnnoController类中的testCookieValue方法！cookievalue:" + cookievalue);
        return "success";
    }


    /**
     * @ModelAttribute：注解放置到方法上，该方法一定在执行控制器方法之前执行
     * 应用场景：用于更新，当表单传递的更新字段 少于 数据库中的字段的时候，使用@ModelAttribute从数据库中查询字段，将数据库字段的值，封装到实体类中，传递给Controller控制器使用
     * 如果jsp传过来的值含有user信息,那么ModelAttribute的值会被传过来的值代替,这里的数据相当于备胎数据
     * 备胎数据
     */
//    @ModelAttribute
//    public void modelAttribute(User user){
//        System.out.println("访问modelAttribute方法！");
//        // 从数据库查询
//        user.setUsername("aaa");
//        user.setAge(22);
//        user.setBirthday(new Date());// 封装
//    }

    /**
     * 4.6 @ModelAttribute注解（了解）
     *
     * @return
     */
    @RequestMapping(value = "/testModelAttribute")
    public String testModelAttribute(User user) {
        System.out.println("欢迎执行AnnoController类中的testModelAttribute方法！user:" + user);
        return "success";
    }

    /**
     * 4.7 @SessionAttributes注解（了解）
     * 作用：
     * 用于多次执行控制器方法间的参数共享。 放置到类的上面。
     * 属性：
     * value：用于指定存入的属性名称
     * type：用于指定存入的数据类型。
     * <p>
     * Model：Map结构，用于响应的时候传递数据（类似于Request的作用域存放数据）
     */
    // 存入sessionAttribute
    @RequestMapping(value = "/sessionAttributePut")
//    public String sessionAttributePut(Model model,User user) {  //也可以同时封装数据
    public String sessionAttributePut(Model model) {   //类的前面不要漏了@SessionAttributes
        System.out.println("欢迎执行AnnoController类中的sessionAttributePut方法！");
//        model.addAllAttributes(集合参数);  //注意区分
        model.addAttribute("username", "张三");
        model.addAttribute("age", 22);
        model.addAttribute("birthday", new Date());
        return "success";
    }

    /**
     * ModelMap：用于取出Model中的数据（相当于从Request作用域取值）
     *
     * @param modelMap
     * @return
     */
    // 取出sessionAttribute
    @RequestMapping(value = "/sessionAttributeGet")
    public String sessionAttributeGet(ModelMap modelMap) {
        System.out.println("欢迎执行AnnoController类中的sessionAttributeGet方法！");
        System.out.println(modelMap.get("username") + "         " + modelMap.get("age") + "        " + modelMap.get("birthday"));
        return "success";
    }

    /**
     * SessionStatus sessionStatus：Session的状态对象，清理Session中的值
     *
     * @param sessionStatus
     * @return
     */
    // 清除sessionAttribute
    @RequestMapping(value = "/sessionAttributeClean")
    public String sessionAttributeClean(SessionStatus sessionStatus) {
        System.out.println("欢迎执行AnnoController类中的sessionAttributeClean方法！");
        sessionStatus.setComplete();  //清除session的全部值
        sessionStatus.isComplete();//是否清除完全部的值
        return "success";
    }
}

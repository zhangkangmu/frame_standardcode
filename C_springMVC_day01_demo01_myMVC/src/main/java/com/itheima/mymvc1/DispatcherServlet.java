package com.itheima.mymvc1;

import com.itheima.util.ClassScannerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 注意：路径千万不要写成/*.do，不符合语法。
 *      写成：*.do，表示客户端的 以.do结尾请求路径，全部由这个Servlet来处理
 * @author liuyp
 * @date 2020/03/02
 */
/*@WebServlet(urlPatterns="*.do")*/
public class DispatcherServlet extends HttpServlet {
    /**
     * 假如客户端请求路径是：   /mymvc/linkMan/queryAll.do
     * 要找到 RequestMapping注解值为 /linkMan/queryAll 的方法，然后调用它
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取客户端请求的路径。 /项目路径/linkMan/query.do
            String uri = request.getRequestURI();
            String contextPath = request.getContextPath();
            String requestPath = uri.substring(contextPath.length(), uri.lastIndexOf("."));
            //System.out.println(requestPath);

            //2. 找到@RequestMapping配置值，和requestPath相等的那个方法，然后调用它
            //2.1 获取并遍历com.itheima.web包里的所有类
            List<Class<?>> classList = ClassScannerUtils.getClasssFromPackage("com.itheima.web");
            for (Class<?> clazz : classList) {

                //2.2 获取类里所有的方法Method对象
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    //2.3 判断方法上是否有@RequestMapping注解
                    boolean isMappingMethod = method.isAnnotationPresent(RequestMapping.class);
                    if (isMappingMethod) {
                        //2.4 获取requestMapping注解对象
                        RequestMapping mapping = method.getAnnotation(RequestMapping.class);

                        String mappingPath = mapping.value();
                        //System.out.println(mappingPath);

                        //2.5 mappingPath和请求路径requestPath相同：执行这个方法
                        if (requestPath.equals(mappingPath)) {
                            method.invoke(clazz.newInstance(), request, response);
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

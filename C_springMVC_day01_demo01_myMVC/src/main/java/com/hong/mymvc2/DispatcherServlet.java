package com.hong.mymvc2;

import com.hong.mymvc1.RequestMapping;
import com.hong.util.ClassScannerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注解@WebServlet里的loadOnStartup：
 *   值是整数。
 *   如果是正整数：当服务器一启动时，就会创建Servlet对象
 *      值越小，创建的越早。
 *
 * @author liuyp
 * @date 2020/03/02
 */
@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private Map<String, MvcMethod> map = new HashMap<>();

    //重写父类的方法，快捷键 ctrl + o。
    //重写无参的init方法：因为init(ServletConfig)方法里，调用了无参的init方法

    /**
     * Servlet的init方法：在Servlet对象被创建时，会执行一次。
     *  默认 用户第一次访问时会创建Servlet对象，init方法会执行一次
     *
     * 要达到的效果是：服务器启动时就初始化，可以使用Servlet的配置项：loadOnStartup
     */
    @Override
    public void init() throws ServletException {
        try {
            List<Class<?>> classList = ClassScannerUtils.getClasssFromPackage("com.itheima.web");
            for (Class<?> clazz : classList) {
                //判断类上是否有@Controller注解。如果没有，就跳过
                boolean isController = clazz.isAnnotationPresent(Controller.class);
                if (!isController) {
                    continue;
                }

                //创建类的实例对象
                Object obj = clazz.newInstance();

                //获取类里所有的方法
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    //如果配置了@RequestMapping
                    boolean isMappingMethod = method.isAnnotationPresent(RequestMapping.class);
                    if (isMappingMethod) {
                        //就要获取@RequestMapping配置的映射路径
                        RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                        String mappingPath = mapping.value();

                        //把方法method和类实例对象obj，封装成MvcMethod
                        MvcMethod mvcMethod = new MvcMethod(method, obj);
                        map.put(mappingPath, mvcMethod);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. 获取客户端请求的路径。 /项目路径/linkMan/query.do
            String uri = request.getRequestURI();
            String contextPath = request.getContextPath();
            String requestPath = uri.substring(contextPath.length(), uri.lastIndexOf("."));

            //2. 从map里找到请求路径对应的MvcMethod对象
            MvcMethod mvcMethod = map.get(requestPath);

            //3. 反射执行MvcMethod里的方法
            Method method = mvcMethod.getMethod();
            Object obj = mvcMethod.getObj();
            method.invoke(obj, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

package com.itheima.mybatis.sqlsession;

/**
 * @author
 * @Company http://www.ithiema.com
 */
public interface SqlSessionFactory {

    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}

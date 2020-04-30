package com.hong.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName StringToDateConverter
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/19 11:51
 * @Version V1.0
 */
public class StringToDateConverter implements Converter<String,Date> {

    // 表单中如果输入的是字符串，而控制器类Controller接收的参数是Date类型的时候，就会执行该方法，如果没有日期类型的参数就不会执行
    @Nullable
    @Override
    public Date convert(String s) {
        if(s==null){
            return null; // 将null值赋值给Date类型
        }
        // 当s不为空，处理类型转换
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 指定参数格式
        Date date = null;
        try {
            date = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.test;

import com.itheima.domain.ClassRoom;
import com.itheima.domain.Student;
import com.itheima.domain.Teacher;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月09日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class BeanWrapperTest {
    @Test
    public void Test(){
        Student studentA = new Student();
        BeanWrapper beanWrapperOfstudentA = PropertyAccessorFactory.forBeanPropertyAccess(studentA);
        PropertyValue studentAName = new PropertyValue("name", "张三");
        beanWrapperOfstudentA.setPropertyValue(studentAName);
        System.out.println(beanWrapperOfstudentA.getWrappedInstance());

        Student studentB = new Student();
        BeanWrapper beanWrapperOfstudentB = PropertyAccessorFactory.forBeanPropertyAccess(studentB);
        PropertyValue studentBName = new PropertyValue("name", "李四");
        beanWrapperOfstudentB.setPropertyValue(studentBName);
        System.out.println(beanWrapperOfstudentB.getWrappedInstance());

        Teacher teacher = new Teacher();
        BeanWrapper beanWrapperOfteacher = PropertyAccessorFactory.forBeanPropertyAccess(teacher);
        PropertyValue teacherSubject = new PropertyValue("subject", "spring源码课程");
        beanWrapperOfteacher.setPropertyValue(teacherSubject);
        System.out.println(beanWrapperOfteacher.getWrappedInstance());

        ClassRoom classRoom = new ClassRoom();
        BeanWrapper beanWrapperOfClassRoom = PropertyAccessorFactory.forBeanPropertyAccess(classRoom);
        //设置班级名称属性
        PropertyValue classRoomName = new PropertyValue("className", "100期");
        beanWrapperOfClassRoom.setPropertyValue(classRoomName);
        //设置学生
        /*Student[] students = {studentA,studentB};
        PropertyValue classRoomStudents = new PropertyValue("students", students);*/
        //beanWrapperOfClassRoom.setPropertyValue(classRoomStudents);
        beanWrapperOfClassRoom.setPropertyValue("students",new ArrayList<>());
        beanWrapperOfClassRoom.setPropertyValue("students[0]",studentA);
        beanWrapperOfClassRoom.setPropertyValue("students[1]",studentB);
        //设置老师
        PropertyValue classRoomTeacher = new PropertyValue("teacher", teacher);
        beanWrapperOfClassRoom.setPropertyValue(classRoomTeacher);
        beanWrapperOfClassRoom.setPropertyValue("teacher.subject","黑马spring源码课程");
        System.out.println(beanWrapperOfClassRoom.getWrappedInstance());
    }
}
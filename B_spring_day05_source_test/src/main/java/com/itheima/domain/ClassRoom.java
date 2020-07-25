package com.itheima.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

public class ClassRoom {
    private String className;
    private Teacher teacher;
    private Student[] students;

    public ClassRoom() {
    }

    public ClassRoom(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
package com.wfr.learning.in.jdk.domain;

import java.util.Date;

/**
 * 学生类
 *
 * @author wangfarui
 * @since 2022/4/18
 */
public class Student {

    private Long id;

    private String name;

    private Date birthday;

    private Classroom classroom;

    public Student() {
        System.out.println("调用 Student 无参构造方法");
    }

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("调用 Student 有参构造方法");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Student clone(Student student) {
        Student s = new Student();
        s.setId(student.getId());
        s.setName(student.getName());
        s.setBirthday(student.getBirthday());
        s.setClassroom(student.getClassroom());
        return s;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", classroom=" + classroom +
                '}';
    }
}

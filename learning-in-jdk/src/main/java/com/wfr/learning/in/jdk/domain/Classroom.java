package com.wfr.learning.in.jdk.domain;

import java.util.List;

/**
 * 班级类
 *
 * @author wangfarui
 * @since 2022/4/18
 */
public class Classroom {

    private Long id;

    private String grade;

    private List<Student> students;

    public Classroom() {
        System.out.println("调用 Classroom 无参构造方法");
    }

    public Classroom(Long id, String grade) {
        this.id = id;
        this.grade = grade;
        System.out.println("调用 Classroom 有参构造方法");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Classroom clone(Classroom classroom) {
        Classroom c = new Classroom();
        c.setId(classroom.getId());
        c.setGrade(classroom.getGrade());
        c.setStudents(classroom.getStudents());
        return c;
    }
}

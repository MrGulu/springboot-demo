package cn.tang.demo.base.service;

import cn.tang.demo.base.entity.Student;

public interface StudentService {

    String sayHello(String name);

    Student getStudentById(Integer id);
}

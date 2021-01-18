package cn.tang.demo.base.service;

import cn.tang.demo.base.entity.Student;

/**
 * @description 测试demo service
 * @author tangwenlong
 */
public interface StudentService {

    String sayHello(String name);

    Student getStudentById(Integer id);
}

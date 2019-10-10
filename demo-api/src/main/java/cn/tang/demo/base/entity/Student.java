package cn.tang.demo.base.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable{

    private Integer id;

    private String name;

    private String age;

    private String sex;

    private String birthday;

    private String address;

    private String score;

    private String mobile;

    private String idno;
}
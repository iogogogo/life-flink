package com.iogogogo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tao.zeng on 2019/1/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    /**
     *
     */
    private long id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private int age;
    /**
     *
     */
    private Date birthday;
    /**
     *
     */
    private int deptId;

    public Employee(String name, int age, Date birthday, int deptId) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.deptId = deptId;
    }
}

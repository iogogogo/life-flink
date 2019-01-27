package com.iogogogo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by tao.zeng on 2019/1/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

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


}

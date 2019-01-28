package com.iogogogo.mapper;

import com.iogogogo.model.Employee;
import com.iogogogo.persistent.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by tao.zeng on 2019/1/27.
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Override
    @Select("select * from employee")
    List<Employee> findAll();

    @Override
    int save(Employee employee);
}

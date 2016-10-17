package com.example.dao;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Script;
import org.seasar.doma.Select;

import com.example.config.MySqlConfig;
import com.example.entity.Employee;

@Dao(config = MySqlConfig.class)
public interface MySqlEmployeeDao {

    @Script
    void dropAndCreate();

    @Select
    List<Employee> selectAll();
    
    @Insert
    int insert(Employee employee);
    
    @BatchInsert(batchSize = 10, exclude = {"id"})
    int[] batchInsert(List<Employee> employeeList);
}

package com.example.dao;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Script;
import org.seasar.doma.Select;

import com.example.config.PostgresConfig;
import com.example.entity.Employee;

@Dao(config = PostgresConfig.class)
public interface PostgresEmployeeDao {
    
    @Script
    void dropAndCreate();

    @Select
    List<Employee> selectAll();
    
    @Insert
    int insert(Employee employee);
    
    @BatchInsert(batchSize = 10, exclude = {"id"})
    int[] batchInsert(List<Employee> employeeList);
}

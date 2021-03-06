package com.example.dao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import com.example.config.PostgresConfig;
import com.example.entity.Employee;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * 実行する際は、pom.xmlにPostgreSQLのJDBCドライバを追加し、
 * MySQLのJDBCドライバを除外してください。
 * @author tada
 */
public class PostgresEmployeeDaoTest {

    private PostgresEmployeeDao employeeDao = new PostgresEmployeeDaoImpl();
    private TransactionManager txManager = PostgresConfig.singleton().getTransactionManager();

    @Before
    public void setUp() {
        txManager.required(() -> {
            employeeDao.dropAndCreate();
        });
    }
    
    /**
     * このテストは成功する
     */
    @Test
    public void 社員を1件追加できる() {
        Employee employee = new Employee("太郎", LocalDate.of(2016, 10, 10));
        int rows = txManager.required(() -> {
            return employeeDao.insert(employee);
        });
        assertThat(rows, is(1));
    }
    
    /**
     * このテストが失敗する→成功した！
     */
    @Test
    public void 社員を3件一括追加できる() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("太郎", LocalDate.of(2016, 10, 10)),
                new Employee("次郎", LocalDate.of(2016, 10, 11)),
                new Employee("三郎", LocalDate.of(2016, 10, 12)));
        int[] rowsList = txManager.required(() -> {
            return employeeDao.batchInsert(employeeList);
        });
        boolean allOne = Arrays.stream(rowsList).allMatch(rows -> rows == 1);
        assertThat(allOne, is(true));
        assertThat(rowsList.length, is(3));
    }
    
}

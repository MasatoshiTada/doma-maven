package com.example.config;

import javax.sql.DataSource;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.Naming;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

@SingletonConfig
public class MySqlConfig implements Config {
    // シングルトンインスタンス
    private static final MySqlConfig CONFIG = new MySqlConfig();
    // SQL方言
    private final Dialect dialect;
    // データソース
    private final LocalTransactionDataSource dataSource;
    // トランザクション管理を行う
    private final TransactionManager transactionManager;
    
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    private MySqlConfig() {
        dialect = new MysqlDialect();
        dataSource = new LocalTransactionDataSource(URL, USER, PASSWORD);
        transactionManager = new LocalTransactionManager(
                dataSource.getLocalTransaction(getJdbcLogger()));
    }
    
    @Override
    public Dialect getDialect() {
        return dialect;
    }
    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }
    @Override
    public Naming getNaming() {
        return Naming.SNAKE_LOWER_CASE;
    }
    public static MySqlConfig singleton() {
        return CONFIG;
    }
}

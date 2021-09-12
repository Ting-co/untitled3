package com.javawebtest.test;

import com.alibaba.druid.util.JdbcUtils;
import com.javawebtest.bean.User;
import com.javawebtest.utils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.Connection;

public class jdbctest {
    @Test
    public void  jdbct() throws Exception {
        Connection connection=null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = jdbcUtils.getConnet();
            String sql="select * from t_user ";
            BeanHandler<User> handler = new BeanHandler<User>(User.class);
            User user = runner.query(connection,sql,handler);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeRES(connection,null);
        }

    }
}

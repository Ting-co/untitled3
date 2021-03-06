package com.javawebtest.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;
import com.mysql.jdbc.Statement;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtils {


    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();


    /*public static Connection getConnection() throws Exception{

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user=pros.getProperty("username");
        String password=pros.getProperty("password");
        String url=pros.getProperty("url");
        String driverClass=pros.getProperty("driverClassName");

        Class.forName(driverClass);

        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }*/

    public  static void closeRES(Connection conn, Statement ps){
        try {
            if (ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void closeRES(Connection conn){
        DbUtils.closeQuietly(conn);
    }


    public  static void closeRES(Connection conn, Statement ps, ResultSet res){
        try {
            if (ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (res!=null){
                res.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static DataSource source;
    static {
        try {
            //??????????????????
            Properties pro =new Properties();
            InputStream is= jdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            pro.load(is);
            //???????????????
            source= DruidDataSourceFactory.createDataSource(pro);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public   static Connection  getConnet() {
        Connection conn =conns.get();
        try {
            conn =source.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * ????????????????????????????????????
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null) { // ???????????????null????????? ??????????????????????????????????????????
            try {
                connection.rollback();//????????????
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // ???????????????????????????
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // ???????????????remove???????????????????????????????????????Tomcat??????????????????????????????????????????
        conns.remove();
    }

    /**
     * ????????????????????????????????????
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null) { // ???????????????null????????? ??????????????????????????????????????????
            try {
                connection.commit(); // ?????? ??????
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // ???????????????????????????
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // ???????????????remove???????????????????????????????????????Tomcat??????????????????????????????????????????
        conns.remove();
    }

}

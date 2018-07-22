package com.wpj.sorm.core;

import com.wpj.sorm.bean.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author wangpj
 * @create 2018-07-22 11:38
 * 根据配置信息，维持连接对象的管理
 */
public class DBManager {
         private static Configuration conf;

         static {
             Properties pro=new Properties();
             try {
                 pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
             } catch (IOException e) {
                 e.printStackTrace();
             }
             conf=new Configuration();
             conf.setDriver(pro.getProperty("driver"));
             conf.setUrl(pro.getProperty("url"));
             conf.setUser(pro.getProperty("user"));
             conf.setPwd(pro.getProperty("pwd"));
             conf.setUsingDB(pro.getProperty("usingDB"));
             conf.setSrcPath(pro.getProperty("srcPath"));
             conf.setPoPackage(pro.getProperty("poPackage"));


         }

     public static Connection getConnection(){
             try {
                 Class.forName(conf.getDriver());
                 //后期增加连接池
                 return DriverManager.getConnection(conf.getUrl(),conf.getUser(),conf.getPwd());
             }catch (Exception e){
                 e.printStackTrace();
                 return null;
             }
     }
    public static void close(ResultSet rs, Statement ps, Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement ps,Connection conn){
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回Configuration对象
     * @return
     */
    public static Configuration getConf(){
        return conf;
    }

}

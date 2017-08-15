
/** 
* @Title: JdbcUtil.java
 * @Package com.company.project.core.dal.util 
 * @Description: TODO
 * Copyright: Copyright (c) 2011 * Company:阅文集团 *
 * @author Comsys-zhangxuewen 
 * @date 2017年7月31日 下午8:51:26 
 * @version V1.0
  */

package com.company.project.core.dal.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName: JdbcUtil
 * @Description: TODO
 * @author Comsys-zhangxuewen
 * @date 2017年7月31日 下午8:51:26
 */

public class JdbcUtil {

    public static Connection conn = null;

    static Statement stsm = null;

    /**
     * 二：获取连接对象 该方法返回一个连接
     */

    public static Connection getConnection(String url, String user, String password) {

        conn = JdbcConnectFactory.getConnection(url, user, password);
        return conn;

    }

    /**
     * 三：释放资源，断开连接 参数列表：conn。stsm
     */

    public static void close(Connection conn, Statement stsm) {
        if (stsm != null) {
            try {
                stsm.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    // 执行DDL语句（创建）
    private static int DDL(final String sql, String url, String user, String password) {
        int result;
        try {

            stsm = (Statement) JdbcUtil.getConnection(url, user, password).createStatement();

            // 发送sql语句
            result = stsm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 调用工具类的方法，关闭连接
            JdbcUtil.close(conn, stsm);
        }

        return result;
    }

    // 执行DML语句（插入）
    private static int DML(String sql, String url, String user, String password) {
        int result;
        try {
            // 使用工具类获取连接对象
            conn = JdbcUtil.getConnection(url, user, password);

            // 创建statement对象
            stsm = conn.createStatement();

            // 执行sql语句
            result = stsm.executeUpdate(sql);
            System.out.println("影响了" + result + "行");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 调用工具类关闭连接
            JdbcUtil.close(conn, stsm);
        }

        return result;
    }

    // 执行DQL语句
    private static int DQL(String sql, String url, String user, String password) {
        int result = 0;
        try {

            // 调用工具类连接对象
            conn = JdbcUtil.getConnection(url, user, password);

            // 创建statement对象
            stsm = conn.createStatement();

            // 执行sql语句,返回的是RrsultSet对象
            ResultSet rs = stsm.executeQuery(sql);

            if (rs != null) {
                result = 1;
            }

            // 查看第二行数据

            // 移动光标
            rs.next();
            rs.next();
            // 使用列名来查看
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            System.out.println(id + "," + name + "," + sex);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 调用工具类关闭连接,这里要多关闭一个连接：ResultSet，工具类的关闭方法要添加它
            JdbcUtil.close(conn, stsm);
        }
        return result;
    }
}

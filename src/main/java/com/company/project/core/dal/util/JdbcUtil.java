
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
import java.sql.DriverManager;
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

    // 创建数据库的基本信息
    // 创建url
    private static String url = "jdbc:mysql://localhost:3306/day1029?useUnicode=true&characterEncoding = GB2312 ";

    // 数据库的用户名和密码
    private static String user = "root";

    private static String password = "root";

    public static Connection conn = null;

    static Statement stsm = null;

    /**
     * 一：注册的驱动程序 获取连接对象的方法 静态代码块（好处是只需要加载一次，且随着类的加载而加载）
     */

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取数据库连接对象出错");
        }
    }

    /**
     * 二：获取连接对象 该方法返回一个连接
     */

    public static Connection getConnection() {

        // 创建连接对象
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
    private static void DDL(final String sql) {

        try {

            stsm = (Statement) JdbcUtil.getConnection().createStatement();

            // 发送sql语句
            int result = stsm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 调用工具类的方法，关闭连接
            JdbcUtil.close(conn, stsm);
        }
    }

    // 执行DML语句（插入）
    private static void DML(String sql) {

        try {
            // 使用工具类获取连接对象
            conn = JdbcUtil.getConnection();

            // 创建statement对象
            stsm = conn.createStatement();

            // 执行sql语句
            int result = stsm.executeUpdate(sql);
            System.out.println("影响了" + result + "行");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 调用工具类关闭连接
            JdbcUtil.close(conn, stsm);
        }
    }

    // 执行DQL语句
    private static void DQL(String sql) {

        try {

            // 调用工具类连接对象
            conn = JdbcUtil.getConnection();

            // 创建statement对象
            stsm = conn.createStatement();

            // 执行sql语句,返回的是RrsultSet对象
            ResultSet rs = stsm.executeQuery(sql);

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
    }
}

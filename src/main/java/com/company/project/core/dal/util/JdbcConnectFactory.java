
/** 
* @Title: JdbcConnectFactory.java
 * @Package com.company.project.core.dal.util 
 * @Description: TODO
 * Copyright: Copyright (c) 2011 * Company:阅文集团 *
 * @author Comsys-zhangxuewen 
 * @date 2017年8月15日 下午1:43:30 
 * @version V1.0
  */

package com.company.project.core.dal.util;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.company.project.core.util.Md5Util;

/**
 * @ClassName: JdbcConnectFactory
 * @Description: TODO
 * @author Comsys-zhangxuewen
 * @date 2017年8月15日 下午1:43:30
 */

public class JdbcConnectFactory {
    private static ConcurrentHashMap<String, DruidPooledConnection> connectioncache = new ConcurrentHashMap<String, DruidPooledConnection>();

    private static ConcurrentHashMap<String, DruidDataSource> dataSourcecache = new ConcurrentHashMap<String, DruidDataSource>();

    private static DruidDataSource dataSource;

    public static DruidPooledConnection getConnection(String url, String username, String pwd) {

        String key = Md5Util.getMD5String(url + username + pwd);
        DruidPooledConnection conn = connectioncache.get(key);
        if (conn == null) {
            conn= JdbcConnectFactory.initConnect(url,  username,  pwd);
            if(conn!=null){
                connectioncache.put(key, conn);
            }
        }
        return conn;

    }

    public static DruidDataSource getDataSource(String url, String username, String pwd) {
        String key = Md5Util.getMD5String(url + username + pwd);
        DruidDataSource datasource = dataSourcecache.get(key);
        if (datasource == null) {
            datasource= JdbcConnectFactory.initDataSource(url,  username,  pwd);
            if(datasource!=null){
                dataSourcecache.put(key, datasource);
            }
        }
        return datasource;
    }

    public static DruidPooledConnection initConnect(String url, String username, String pwd) {
        dataSource = new DruidDataSource();
        DruidPooledConnection conn = null;
        try {
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername(username);
            dataSource.setPassword(pwd);
            dataSource.setUrl(url);
            dataSource.setInitialSize(5);
            dataSource.setMinIdle(1);
            dataSource.setMaxActive(10); // 启用监控统计功能

            dataSource.setFilters("stat");
            // for mysql
            dataSource.setPoolPreparedStatements(false);

            conn = dataSource.getConnection();
        } catch (SQLException e) {

        }
        return conn;
    }

    public static DruidDataSource initDataSource(String url, String username, String pwd) {
        dataSource = new DruidDataSource();

        try {
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername(username);
            dataSource.setPassword(pwd);
            dataSource.setUrl(url);
            dataSource.setInitialSize(5);
            dataSource.setMinIdle(1);
            dataSource.setMaxActive(10); // 启用监控统计功能

            dataSource.setFilters("stat");
            // for mysql
            dataSource.setPoolPreparedStatements(false);

        } catch (SQLException e) {

        }
        return dataSource;
    }
}

package com.myJavaTest.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.myJavaTest.dao.baseDao ;

/**
 * baseDao实现
 * @author rovo98
 *
 */
public class baseDaoImpl implements baseDao{
	private String driver = "com.mysql.jdbc.Driver" ;   // 数据库连接驱动类路径
	private String url = "" ;                           // 数据库连接地址
	private String user = "" ;                          // 数据库用户名
	private String password = "" ;                      // 用户密码
	private Connection conn = null ;
	
	/**
	 * 获取数据库连接
	 * @return   返回数据库连接
	 */
	public Connection getConnection() {
		
		try {
			Class.forName(driver) ;
			conn = DriverManager.getConnection(url,user,password) ;
		}catch (ClassNotFoundException e) {
			e.printStackTrace() ;
		}catch (SQLException e) {
			e.printStackTrace() ;
		}
		
		return conn ;
	}
	/**
	 * 关闭数据库连接
	 */
	public void closeConnection() {
		
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close() ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

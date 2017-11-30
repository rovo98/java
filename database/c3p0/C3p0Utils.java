package com.xiaotuanti.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource ;

/**
 * 
 * @author rovo98
 * c3p0数据库连接池
 */
public class C3p0Utils {
	
	// 通过标识符来创建相应的数据库连接池
	private static ComboPooledDataSource c3p0DataSource ;
	// 静态加载配置参数
	static {
		c3p0DataSource  = new ComboPooledDataSource("mysql") ;
	}
	
	/**
	 * 从连接池中获取一个数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return c3p0DataSource.getConnection() ;
		}catch (Exception e) {
			e.printStackTrace() ;
			return null ;
		}
	}
	/**
	 * 释放连接返回连接池
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close() ;
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close() ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close() ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

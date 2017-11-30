package com.xiaotuanti.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBUtil_BO类
 * 用于封装一个数据库连接，一个Statement和preparedStatement
 * 在DAO层的一次数据库操作可以封装在这个bo对象中，提取时访问结果集rs
 * @author rovo98
 * 
 */
public class DBUtil_BO {
	public Connection conn = null ;
	public PreparedStatement pstmt = null ;
	public ResultSet rs = null ;
	public DBUtil_BO() {
		super() ;
	}
  // 事务管理
	public void setAutoCommit(boolean autoCommit) {
		try {
			conn.setAutoCommit(autoCommit) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
  // 事务提交
	public void commit() {
		try {
			conn.commit() ;
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
  // 事务回滚
	public void rollback() {
		try {
			conn.rollback() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

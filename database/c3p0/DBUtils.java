package com.rovo98.C3p0PoolTest.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库操作类
 * 负责执行数据库操作封装bo对象以及释放连接回连接池，并把结果封装回bo对象
 * @author rovo98
 * 
 */
public class DBUtils {

	/**
	 * 释放资源
	 * @param _conn
	 * @param _pstmt
	 * @param _rs
	 */
    private static void realseSource( Connection _conn, PreparedStatement _pstmt,ResultSet _rs){        
           C3p0Utils.closeConnection(_conn,_pstmt,_rs);
       }

       public static void realseSource(DBUtil_BO _bo){    
           if(_bo != null){
               realseSource(_bo.conn, _bo.pstmt, _bo.rs);
           }        
       }
       //查询操作完成后，因为还需提取结果集中信息，所以仍保持连接，在结果集使用完后才通过DBUtils.realseSource()手动释放连接
       public static void executeQuery(DBUtil_BO bo)
       {        
           try{
               bo.rs = bo.pstmt.executeQuery();
           }catch (SQLException e){ 
        	   e.printStackTrace() ;
               realseSource(bo);
           }    
       }
       
      //而update操作完成后就可以直接释放连接了，所以在方法末尾直接调用了realseSourse()
       public static  void executeUpdate(DBUtil_BO bo)
       {

           Connection conn = bo.conn;
           PreparedStatement st = bo.pstmt;
           try {
               st.executeUpdate();
               conn.commit() ;
           } catch (SQLException e) {
        	   e.printStackTrace() ;
               realseSource(conn, st, null);        
           }
           realseSource(conn, st,null );                

       }
}

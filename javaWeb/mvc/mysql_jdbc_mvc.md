---
author: rovo98
---

# mysql数据库查询记录

  简单查询数据库中的物流数据库的公司记录根据公司所有者的性别分类查询
  
### dao层

如果使用传统的JDBC数据连接方式，**定义baseDao.java和baseDaoImpl.java**,如下：

```java
package com.rovo98.Test.dao ;

import java.sql.Connection ;

public interface baseDao {
	Connection getConnection() ; // 获取数据库连接
    void closeConnection() ; // 释放数据库连接
}
```

```java
package com.rovo98.Test.dao.impl ;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;

import com.rovo98.Test.dao.baseDao ;

public class baseDaoImpl implements baseDao {
	private String driver = "com.mysql.jdbc.Driver" ; // 数据库连接驱动
    private String url = "" ；  //数据库连接地址
    private String user = "" ;  // 数据库用户名
    private String password = "" ; // 用户密码
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
```
c3p0数据库连接池具体使用，请看我的另外一篇:[查看](https://github.com/rovo98/java-learning/blob/master/database/c3p0/README.md)
#### 简单定义公司的dao enterpriseDao.java和enterpriseDaoImpl.java
```java
package com.rovo98.Test.dao;

import java.util.LinkedList;

import com.rovo98.Test.model.Enterprise;

public interface enterpriseDao {
	// 获取所有物流公司信息，根据公司所属人的性别
	LinkedList<Enterprise> queryEnterpriseByLinkMan(String gender) ; 
}
```

```java
package com.rovo98.Test.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;

import com.rovo98.DBUtils.C3p0Utils;
import com.rovo98.DBUtils.DBUtil_BO;
import com.rovo98.DBUtils.DBUtils;
import com.rovo98.model.Enterprise;
import com.rovo98.Test.dao.enterpriseDao;

public class enterpriseDaoImpl implements enterpriseDao {
	DBUtil_BO dbBo = new DBUtil_BO() ;   // 一次数据库操作对象封装
	
	public LinkedList<Enterprise> queryEnterpriseByLinkMan(String gender) {
		String sql = "SELECT * FROM tb_enterprise WHERE linkman = ?" ;
		dbBo.conn = C3p0Utils.getConnection() ;        // 从连接池中获取数据库连接
		dbBo.setAutoCommit(false) ;                   // 取消事务自动提交
		LinkedList<Enterprise> list = null ;
		
		try {
			dbBo.pstmt = dbBo.conn.prepareStatement(sql) ;
			dbBo.pstmt.setString(1, "_" + gender) ;
			
			DBUtils.executeQuery(dbBo) ;
			dbBo.commit() ;                          // 事务提交
			list = new LinkedList<Enterprise>() ;
			
			while (dbBo.rs.next()) {
				Enterprise ep = new Enterprise() ;
				ep.setAddress(dbBo.rs.getString("Address")) ;
				ep.setEmail(dbBo.rs.getString("Email")) ;
				ep.setEnterpriseName(dbBo.rs.getString("EnterpriseName")) ;
				ep.setEnterpriseSort(dbBo.rs.getString("EnterpriseSort")) ;
				ep.setFax(dbBo.rs.getString("Fax")) ;
				ep.setHandSet(dbBo.rs.getString("HandSet")) ;
				ep.setHttp(dbBo.rs.getString("Http")) ;
				ep.setIntro(dbBo.rs.getString("Intro")) ;
				ep.setIssueDate(dbBo.rs.getString("IssueDate")) ;
				ep.setLinkMan(dbBo.rs.getString("LinkMan")) ;
				ep.setOperation(dbBo.rs.getString("Operation")) ;
				ep.setPhone(dbBo.rs.getString("Phone")) ;
				ep.setUserName(dbBo.rs.getString("UserName")) ;
				ep.setWorkArea(dbBo.rs.getString("WorkArea")) ;
				
				list.add(ep) ;
			}
			DBUtils.releaseSource(dbBo) ;               // 释放数据库操作对象
			return list ;
		}catch(SQLException e) {
			e.printStackTrace() ;
			dbBo.rollback() ;                         // 事务回滚
			DBUtils.releaseSource(dbBo) ;
		}
		return list ;
	}
}
```
### model层

#### 定义封装数据的javabean Enterprise.java

```java
package com.rovo98.Test.model;

public class Enterprise {
	private String enterpriseSort ;
	private String enterpriseName ;
	private String operation ;
	private String workArea ;
	private String address ;
	private String phone ;
	private String linkMan ;
	private String handSet ;
	private String fax ;
	private String email ;
	private String http ;
	private String intro ;
	private String issueDate ;
	private String userName ;
	
	public String getEnterpriseSort() {
		return enterpriseSort;
	}
	public void setEnterpriseSort(String enterpriseSort) {
		this.enterpriseSort = enterpriseSort;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getWorkArea() {
		return workArea;
	}
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getHandSet() {
		return handSet;
	}
	public void setHandSet(String handSet) {
		this.handSet = handSet;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHttp() {
		return http;
	}
	public void setHttp(String http) {
		this.http = http;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
```

### service业务层

   由于该程序测试相对简单，就不编写service层实现了。
   
### control层

#### 相关controller的定义 enterpriseServlet.java

```java
package com.rovo98.Test.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rovo98.Test.model.Enterprise;
import com.rovo98.Test.dao.enterpriseDao;
import com.rovo98.Test.dao.impl.enterpriseDaoImpl;


@WebServlet("/sqlTest")
public class sqlTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public sqlTestServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接受传递过来的参数信息,默认前端已经做了数据验证过滤
		String gender = null ;
		int type = Integer.parseInt(request.getParameter("gender")) ;
		if (type == 0) {
			gender = "先生" ;
		}
		else {
			gender = "女士" ;
		}
		enterpriseDao epd = new enterpriseDaoImpl() ;
		LinkedList<Enterprise> data = epd.queryEnterpriseByLinkMan(gender) ;
		
		// 将数据存放到request中,并请求转发到结果显示页面处理
		request.setAttribute("gender", type) ;
		request.setAttribute("data", data) ;
		request.getRequestDispatcher("/servletTest/sqlTestResult.jsp").forward(request, response) ;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
```

### viewer 视图层

#### 定义视图 sqlTest.jsp 和 sqlTestResult.jsp

- sqlTest.jsp
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>sql数据查询测试</title>
<style type="text/css">
	#sqlTest {
		width : 300px ;
		height : 100px ;
		margin : 0 auto ;
	}
</style>
</head>
<body>
	<h3 align="center">查询db_wuliu数据库中的物流公司记录</h3>
	<div id="sqlTest">
	<form action="<%=request.getContextPath() %>/sqlTest" name="sqlTest" method="post">
		公司所属人筛选条件:
		<select name="gender">
			<option value="0">男士</option>
			<option value="1">女士</option>
		</select>
		<br />
		<input type="submit" value="查询" />
		&nbsp;&nbsp;
		<input type="reset" value="重置" />
	</form>
	</div>
</body>
</html>
```

- sqlTestResult.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>sql数据查询测试结果</title>
<style type="text/css">
	table {text-align:center;margin:50px auto;background-color:#ccc;}
	.header {background-color:#9c3;text-align:center;}
</style>
</head>
<body>
	<h3 align="center">查询db_wuliu数据库中的公司记录结果</h3>
	
	<c:if test="${ requestScope.gender == 0 }">
	<h4 align="center">公司记录表:筛选条件 -->先生</h4>
	</c:if>
	<c:if test="${ requestScope.gender == 1 }">
	<h4 align="center">公司记录表:筛选条件 -->女士</h4>
	</c:if>
	<!-- 后天无数据返回时 -->
	<c:if test="${ fn:length(data) eq 0 }">
		<span>查询结果不存在</span>
	</c:if>
	<!-- 后台有数据返回时 -->
	<c:if test="${ fn:length(data) gt 0 }">
	<table style="border-collapse: collapse" cellspacing="0px">
		<thead>
			<tr class="header">
				<th>EnterpriseSort</th>
				<th>EnterpriseName</th>
				<th>Operation</th>
				<th>WorkArea</th>
				<th>Address</th>
				<th>Phone</th>
				<th>LinkMan</th>
				<th>HandSet</th>
				<th>Fax</th>
				<th>Email</th>
				<th>Http</th>
				<th>Intro</th>
				<th>IssueDate</th>
				<th>UserName</th>
			</tr>
		</thead>
		<c:forEach items="${ data }" var="ep">
			<tr>
				<td><c:out value="${ ep.enterpriseSort }"></c:out></td>
				<td><c:out value="${ ep.enterpriseName }"></c:out></td>
				<td><c:out value="${ ep.operation }"></c:out></td>
				<td><c:out value="${ ep.workArea }"></c:out></td>
				<td><c:out value="${ ep.address }"></c:out></td>
				<td><c:out value="${ ep.phone }"></c:out></td>
				<td><c:out value="${ ep.linkMan }"></c:out></td>
				<td><c:out value="${ ep.handSet }"></c:out></td>
				<td><c:out value="${ ep.fax }"></c:out></td>
				<td><c:out value="${ ep.email }"></c:out></td>
				<td><c:out value="${ ep.http }"></c:out></td>
				<td><c:out value="${ ep.intro }"></c:out></td>
				<td><c:out value="${ ep.issueDate }"></c:out></td>
				<td><c:out value="${ ep.userName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>
```

测试结果 ：

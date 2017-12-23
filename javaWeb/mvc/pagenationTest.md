---
author: rovo98
---

# 分页查询
> 从db_wuliu数据库中获取tb_goodsmeg数据并分页显示

### model层

#### 定义封装一次分页信息的对象Page.java（通用）和封装记录数据的对象Goods.java

```java
package com.rovo98.Test.model;

import java.util.List;

/*
*  @author: rovo98
*
*/
public class Page<E> {
	private int totalRecords ; // 总记录数
	private int pageSize ;     // 单页数据量
	private int pageNo ;       // 当前页码
	private List<E> list ;
	
	// 获取总页面数
	public int getTotalPages() {
		return (totalRecords + pageSize -1) / pageSize ;
	}
	// 获取第一页
	public int getTopPageNo() {
		return 1 ;
	}
	// 获取尾页
	public int getBottomPageNo() {
		return getTotalPages() ;
	}
	// 获取下一页
	public int getNextPageNo() {
		
		if (pageNo >= getBottomPageNo()) {
			return getBottomPageNo() ;
		}
		return pageNo + 1 ;
	}
	// 获取上一页
	public int getPreviousPageNo() {
		
		if (pageNo <= 1) {
			return 1 ;
		}
		return pageNo - 1 ;
	}
    
	// setter和getter
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	
}
```

```java
package com.rovo98.Test.model;

/**
 * 
 * @author rovo98
 *
 */
public class Goods {
	private String goodsStyle ;
	private String goodsName ;
	private String goodsNumber ;
	private String goodsUnit ;
	private String startProvince ;
	private String startCity ;
	private String endProvince ;
	private String endCity ;
	public String getGoodsStyle() {
		return goodsStyle;
	}
	public void setGoodsStyle(String goodsStyle) {
		this.goodsStyle = goodsStyle;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getStartProvince() {
		return startProvince;
	}
	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getEndProvince() {
		return endProvince;
	}
	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	
	
}

```
### dao层

#### 定义goodsDao.java和goodsDaoImpl.java

```java
package com.rovo98.Test.dao;

import com.rovo98.Test.model.Goods;
import com.rovo98.Test.model.Page;

/*
* @author rovo98
*/
public interface goodsDao {
	Page<Goods> getOnePageGoods(int pageNo, int pageSize) ;  // 获取单页货物信息数据
}
```

```java
package com.rovo98.mvc.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;

import com.rovo98.DBUtils.C3p0Utils;
import com.rovo98.DBUtils.DBUtil_BO;
import com.rovo98.DBUtils.DBUtils;
import com.rovo98.Test.model.Goods;
import com.rovo98.Test.model.Page;
import com.rovo98.Test.dao.goodsDao ;
/**
 * 
 * @author rovo98
 */
public class goodsDaoImpl implements goodsDao {
	DBUtil_BO dbBo = new DBUtil_BO() ;
	
	public Page<Goods> getOnePageGoods(int pageNo, int pageSize) {
		String sql_1 = "SELECT * FROM tb_goodsmeg LIMIT ?,?" ;
		String sql_2 = "SELECT COUNT(*) FROM tb_goodsmeg" ;
		Page<Goods> page = null ;
		LinkedList<Goods> list = null ;
		dbBo.conn = C3p0Utils.getConnection() ;
		dbBo.setAutoCommit(false) ;
		
		try {
			dbBo.pstmt = dbBo.conn.prepareStatement(sql_1) ;
			dbBo.pstmt.setInt(1, (pageNo-1) * pageSize) ;
			dbBo.pstmt.setInt(2, pageNo * pageSize) ;
			
			DBUtils.executeQuery(dbBo) ;
			page = new Page<Goods>() ;
			list = new LinkedList<Goods>() ;
			
			while (dbBo.rs.next()) {
				Goods goods = new Goods() ;
				goods.setEndCity(dbBo.rs.getString("EndCity")) ;
				goods.setEndProvince(dbBo.rs.getString("EndProvince")) ;
				goods.setGoodsName(dbBo.rs.getString("GoodsName")) ;
				goods.setGoodsNumber(dbBo.rs.getString("GoodsNumber")) ;
				goods.setGoodsStyle(dbBo.rs.getString("GoodsStyle")) ; 
				goods.setGoodsUnit(dbBo.rs.getString("GoodsUnit")) ;
				goods.setStartCity(dbBo.rs.getString("StartCity")) ;
				goods.setStartProvince(dbBo.rs.getString("StartProvince")) ;
				
				list.add(goods) ;
			}
			
			dbBo.pstmt = dbBo.conn.prepareStatement(sql_2) ;
			DBUtils.executeQuery(dbBo) ;
			dbBo.commit() ;
			
			if (dbBo.rs.next()) {
				page.setTotalRecords(dbBo.rs.getInt(1)) ;
			}
			page.setPageNo(pageNo) ; 
			page.setPageSize(pageSize) ;
			page.setList(list) ;
			
			DBUtils.releaseSource(dbBo) ;
			return page ;
		}catch (SQLException e) {
			e.printStackTrace() ;
			dbBo.rollback() ;
			DBUtils.releaseSource(dbBo)  ;
			return page ;
		}
	}
}

```
### service业务层
> 由于该分页测试程序简单，可以不写

### controller控制层

#### 定义处理前端请求的servlet pagenationServlet.java

```java
package com.rovo98.Test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rovo98.Test.model.Goods;
import com.rovo98.Test.model.Page;
import com.rovo98.Test.dao.goodsDao;
import com.rovo98.Test.dao.impl.goodsDaoImpl;


/**
 * 
 * @author rovo98
 */
@WebServlet("/pagenationTest")
public class pagenationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public pagenationServlet() {
        super();
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接受前端传递的参数数据,默认做了过滤
		int pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		System.out.println(pageNo);
		System.out.println(pageSize);
		// 查询当前分页数据并请求重定向到结果显示页面
		goodsDao gd = new goodsDaoImpl() ;
		Page<Goods> page = gd.getOnePageGoods(pageNo, pageSize) ;
		
		System.out.println(page.getList().size()) ;
		request.setAttribute("data", page) ;
		request.getRequestDispatcher("/servletTest/pagenationTestResult.jsp").forward(request, response) ;
	}

	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

```

### viewer显示层

#### 定义显示页面 pagenationTest.jsp和pagenationTestResult.jsp

- pagenationTest.jsp
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页查询测试</title>
<style type="text/css">
	div {
		width : 150px ;
		height : 50px ;
		margin: 0 auto ;
	}
</style>
</head>
<body>
	<h3 align="center">分页查询: 服务器端分页测试，由于不使用AJAX异步请求，此页面充当请求页面</h3>
	<h4 align="center">查询tb_goodsmeg表的中的部分信息，分页显示</h4>
	<div>
	<form action="<%=request.getContextPath() %>/pagenationTest" name="pagenationTest" method="post">
		起始页码: <input type="radio" value="1" name="pageNo" checked="checked"/>不可选
		<br />
		单页数量: <input type="text" name="pageSize" />
		<br />
		<input type="submit" value="查询" />&nbsp;&nbsp;
		<input type="reset" value="重置" />
	</form>
	</div>
</body>
</html>
```

- pagenationTestResult.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页查询测试</title>
<style type="text/css">
	table {text-align:center;margin:50px auto;background-color:#ccc;}
	.header {background-color:#9c3;text-align:center;}
</style>
</head>
<body>
    <h3 align="center">查询结果</h3>
    <c:if test="${fn:length(data.list) eq 0}">
    	<h4 align="center">查询不到结果</h4>
    </c:if>
    <c:if test="${fn:length(data.list) gt 0}">
		<table width="786"  border="1" align="center">
		<tr>
	  		<td width="786" colspan="9"><p align="center">货物信息</td>
	  	</tr>
		  <tr align="center" class="header">
		    <td width="786" height="29" align="center">货物类型</td>
		    <td width="786" height="29" align="center">货物名称</td>
		    <td width="786" height="29" align="center">货物数量</td>
		    <td width="786" height="29" align="center">数量单位</td>
		    <td width="786" height="29" align="center">起始省份</td>
		    <td width="786" height="29" align="center">起始城市</td>
		    <td width="786" height="29" align="center">抵达省份</td>
		    <td width="786" height="29" align="center">抵达城市</td>
		  </tr>
		<c:forEach items="${data.list}" var="goods">
		<tr>
			<td width="786" height="29" align="center"><c:out value="${ goods.goodsStyle }"></c:out></td>
			<td width="786" height="29" align="center"><c:out value="${ goods.goodsName }"></c:out></td>
			<td width="786" height="29" align="center"><c:out value="${ goods.goodsNumber }"></c:out></td>
			<td width="786" height="29" align="center"><c:out value="${ goods.goodsUnit }"></c:out></td>
			<td width="786" height="29" align="center"><c:out value="${ goods.startProvince }"></c:out></td>
			<td width="786" height="29" align="center"><c:out value="${ goods.startCity }"></c:out></td>
			<td width="786" height="29" align="center"><c:out value="${ goods.endProvince }"></c:out></td>
			<td width="786" height="29" align="center"><c:out value="${ goods.endCity }"></c:out></td>
		<tr>
		</c:forEach>
	<tr>
		<td width="786" height="30" colspan="9" align="right">
		<table width="786" align="center">
			<tr>
		    <td width="786" height="30" colspan="9" align="right">
		   共 <c:out value="${ data.totalRecords }"></c:out>条记录&nbsp;&nbsp;
		    第<c:out value="${ data.pageNo }"></c:out>页&nbsp;&nbsp;
		    共<c:out value="${ data.getTotalPages() }"></c:out>页&nbsp;&nbsp;
		        <a href="<%=request.getContextPath()%>/pagenationTest?pageNo=${ data.getTopPageNo() }&pageSize=${ data.pageSize }">第一页</a>&nbsp;&nbsp;
		        <a href="<%=request.getContextPath()%>/pagenationTest?pageNo=${ data.getPreviousPageNo() }&pageSize=${ data.pageSize }">上一页</a>&nbsp;&nbsp;
		        <a href="<%=request.getContextPath()%>/pagenationTest?pageNo=${ data.getNextPageNo() }&pageSize=${ data.pageSize }">下一页</a>&nbsp;&nbsp;
		        <a href="<%=request.getContextPath()%>/pagenationTest?pageNo=${ data.getBottomPageNo() }&pageSize=${ data.pageSize }">最后一页</a>
			</td>
			</tr>
		</table>
		</td>
	</tr>
	</table>
</c:if>
</body>
</html>
```

### 测试结果


### 总结：分页查询的三种不同方式

#### 1.纯js实现的的分页

   一次性查询记录并加载到显示页面中，然后通过选择性的显示某些行来达到分页显示的目的。
这是一种**伪分页**。存在很多缺点：

1. 只能**适用于数据量比较少**的情况。数据量大时，一次性的将数据加载到页面会严重影响显示页面的加载速度。影响用户体验。
2. 显示的数据**不是实时的**，由于数据是一次性加载到页面中，页面显示的数据并不是数据库中最新的数据。

**[注]:该分页方式一般在实际开发中不使用**

#### 2.一次查询，分批显示

  执行一次数据库查询操作，得到结果集rs。然后，通过指针的移动来显示当前记录。使用**rs.absolue(当前页面号X每页记录数)**定位到当前页的第一条记录，然后循环显示n(每页的数据显示数量)条记录。特点 ：
  
1. 与js方式不同的是：此种方式**选择性遍历需要遍历的数据**，每一次跳页都需要修改遍历的指针，每次都要进行一次全面查询，不适合**大数据量的查询**。
2. 相对于js方式，由于每次跳页都查询一次数据库，页面显示的数据是**实时的**。


#### 3.在服务器端分页

  跳转到n页，才查询第n页的数据。拿MySQL数据库来说；根据页面计算当前页的第一个记录的位置。使用:
  ```sql
  select count(*） from tb  ; -- 查询得到记录的总数
  select * from tb limit pageNo,rowsCount ; -- 查询从第pageNo条记录到rowsCount条记录
  ```
 特点 ：
 - 实时性、跳页才查询，数据量小；只加载当前页的记录显示。 

---
author: rovo98
---

# MVC模式设计实验
> 计算三角形的面积


### model层

#### 定义封装三角形属性和基本方法的javabean

```java
package com.rovo98.Test.model ;

class Trangle {
	private double sideA ; // 边a
    private double sideB ; // 边b
    private double sideC ; // 边c
    
    // getter和setter方法定义
    public void setSideA(double sideA) {
    	this.sideA = sideA ;
    }
        public void setSideB(double sideB) {
    	this.sideB = sideB ;
    }
        public void setSideC(double sideC) {
    	this.sideC = sideC ;
    }
    public double getSideA() {
    	return this.sideA ;
    }
    public double getSideB() {
    	return this.sideB ;
    }
    public double getSideC() {
    	return this.sideC ;
    }
    // 判断是否能构成三角形
    public boolean isTrangle() {
    	if (this.sideA + this.sideB < this.sideC 
        || this.sideA + this.sideC < this.sideB
        || this.sideB + this.sideC < this.sideA) {
        	return false ;
        }
        else {
        	return true ;
        }
    }
    // 计算三角形面积
    public double getArea() {
    	if (this.isTrangle()) {
        	double p = (a + b + c) / 2 ;
            return Math.sqrt(p * (p - a)*(p - b)*(p - c) ;
        }
        else {
        	return -1 ;
        }
    }
}
```

### viewer层

#### 定义视图显示

  即jsp页面
  
- 信息输入页面
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>三角形面积计算MVC设计</title>
<style type="text/css">
	table {
		width : 200px ;
		height : 100px ;
		text-align : center ;
		margin : 0 auto ;
	}
</style>
</head>
<body>
	<h3 align="center">三角形面积计算mvc设计</h3>
	<form action="" name="inputSource" method="post">
		<table>
			<tr>
				<td colspan="2">输入三角形信息</td>
			</tr>
			<tr>
				<td>边A:</td>
				<td><input type="text" name="sideA" /></td>
			</tr>
			<tr>
				<td>边B:</td>
				<td><input type="text" name="sideB" /></td>
			</tr>
			<tr>
				<td>边C:</td>
				<td><input type="text" name="sideC" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="计算" /></td>
				<td><input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
```

- 结果显示结果

  因为mvc的架构目的在于程序逻辑代码和页面jsp代码分离，所以我使用了jstl（jsp标签库)的一些核心标签和EL表达式来做页面显示
  
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>结果显示页面</title>
</head>
<body>
	<h3 align="center">三角形面积计算结果显示页面</h3>
	<h4 align="center">
	<c:if test="${ requestScope.result == -1 }">
		<c:out value="输入的三条边无法构成一个三角形，不能计算面积!"></c:out>
	</c:if>
	<c:if test="${ requestScope.result > 0 }">
		<c:out value="面积为: ${requestScope.result }"></c:out>
	</c:if>
	</h4>
</body>
</html>
```

### controller层

#### 定义处理前端请求的控制器
由于改程序没有涉及到数据库操作，就简单的使用servlet做逻辑运算和页面的跳转，servlet的配置方式也使用方便简单的注解方式配置，不使用xml（特别是编写servlet数量较多的情况)

```java
package com.rovo98.Test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rovo98.model.Trangle;

@WebServlet("/trangleTest")
public class trangleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public trangleServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接受表单传递过来的参数信息
		Trangle trangle = new Trangle() ;
		// 这里默认前端已经做了基本数据验证过滤
		trangle.setSideA(Double.parseDouble(request.getParameter("sideA"))) ;
		trangle.setSideB(Double.parseDouble(request.getParameter("sideB"))) ;
		trangle.setSideC(Double.parseDouble(request.getParameter("sideC"))) ;
		
		// 计算并将结果传递到结果显示页面，不同也AJAX异步请求的处理
        // 使用请求转发方式，客户端地址栏不发生改变，让用户觉得程序在同一个页面处理
		request.setAttribute("result", trangle.getArea()) ;
		request.getRequestDispatcher("/trangleResult.jsp").forward(request, response) ;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
```

### 测试结果

页面 ：


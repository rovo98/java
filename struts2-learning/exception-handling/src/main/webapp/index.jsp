<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<% String path = request.getContextPath(); %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/7
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basic Struts 2 Application - Welcome</title>
    <s:head />
</head>
<body>
<h1>Welcome To Struts 2!</h1>

<p><a href="<s:url action='hello' />">Hello World</a></p>

<s:url action="hello" var="helloLink">
    <s:param name="userName">rovo98</s:param>
</s:url>
<p><a href="${helloLink}">Hello, rovo98!</a></p>

<p>Get your own personal hello by filling out and submitting this form.</p>
<s:form action="hello">
    <s:textfield name="userName" label="Your name" />
    <s:submit value="Submit" />
</s:form>

<s:url action="registerInput" var="registerInputLinkEN">
    <s:param name="request_locale">en</s:param>
</s:url>
<s:url action="registerInput" var="registerInputLinkCN">
    <s:param name="request_locale">zh_CN</s:param>
</s:url>
<p><a href="${registerInputLinkEN}">Please com.rovo98.struts.register</a> for our prize drawing.</p>
<p><a href="${registerInputLinkCN}">请完成注册</a>来参加抽奖活动.</p>
<hr>

<p><a href="register.jsp">Please register</a> for our prize drawing.</p>
<p><a href='<s:url action="causeexception" />'>Cause Exception</a></p>
<p><a href='<s:url action="causenullpointerexception" />'>Cause Null Pointer Exception</a></p>
<p><a href='<s:url action="causesecurityexception" />'>Cause Global Security Exception</a></p>
<p><a href='<s:url action="actionspecificexception" />'>Cause ActionSpecific Security Exception</a></p>

<hr/>

<h3>Debugging</h3>

<p><a href="<s:url action="index" namespace="config-browser" />">Launch the configuration browser</a></p>
<s:url action="index" var="indexLink">
    <s:param name="debug">browser</s:param>
</s:url>
<p><a href="${indexLink}">Reload this page with debugging</a></p>

<s:debug/>
<s:text name="contact" />
</body>
</html>

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
<p><a href="${registerInputLinkEN}">Please register</a> for our prize drawing.</p>
<p><a href="${registerInputLinkCN}">请完成注册</a>来参加抽奖活动.</p>
<hr>
<s:text name="contact" />
</body>
</html>

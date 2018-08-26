<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/11

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Struts 2 Form Tags - Welcome</title>
</head>
<body>
<h1><s:text name="Welcome" /></h1>

<h3><s:text name="msg" /></h3>

<s:url action="edit" var="editLink">
    <s:param name="request_locale">en</s:param>
</s:url>
<s:url action="edit" var="editLinkCN">
    <s:param name="request_locale">zh_CN</s:param>
</s:url>
<p><a href="${editLink}">Edit your information</a></p>
<p><a href="${editLinkCN}">编辑更新你的信息</a></p>
</body>
</html>

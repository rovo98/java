<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/11

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Successful</title>
    <s:head />
</head>
<body>
<p>Your Update information: <s:property value="personBean"/></p>

<s:if test="personBean.over21">
    <p>You are old enough to vote!</p>
</s:if>
<s:else>
    <p>You are NOT old enough to vote.</p>
</s:else>

<s:if test="personBean.carModels.length > 1">
    <p>Car models
</s:if>
<s:else>
    <p>Car model
</s:else>
    selected: </p>

<table style="margin-left:15px">
    <s:iterator value="personBean.carModels">
        <tr>
            <td><s:property /></td>
        </tr>
    </s:iterator>
</table>

<p>States you may have selected:</p>

<table style="margin-left:15px">
    <s:iterator value="states">
        <tr>
            <td><s:property value="stateAbbr" /></td>
            <td><s:property value="stateName" /></td>
        </tr>
    </s:iterator>
</table>
<p><a href="<s:url action='index'/>">Return to home page</a></p>
</body>
</html>

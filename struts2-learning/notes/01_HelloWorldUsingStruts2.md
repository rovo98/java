---
author : rovo98
date: 2018.4.4
description: learning from struts.apache.org.
---

# Hello World Demo - 使用Struts 2 框架

## Table of Contents

- [引入](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#引入)
- [具体实现](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#具体实现----go-back-to-top)
    - [Step 1:创建model](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#step-1)
    - [Step 2:创建controller](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#step-2)
    - [Step 3:创建view](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#step-3----go-back-to-top)
    - [Step 4:在struts.xml中添加映射](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#step-4)
    - [Step 5:在某个页面创建Action URL](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#step-5----go-back-to-top))
- [What to Remember](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#what-to-remember----go-back-to-top))

## 引入

当我们在使用了Struts 2框架的Web项目的页面中点击一个超链接或者提交一个表单时，我们所
提交的输入信息通常并不是提交到另外一个页面。而是提交给被我们称为Action的Java类。
Action处理完后，结果选择一个资源来进行响应，响应的资源通常是一个页面，
当然还可以是一个PDF文件，Excel文档或者一个Java applet窗口。

使用struts 2框架简单地实现一个hello world demo,需要做一下的准备：

1. 创建一个类来保存信息(message) (the model);
2. 创建一个jsp页面来展示信息 (the view);
3. 创建一个Action类来控制用户和 model 和 view 的交互;
4. 在struts.xml 添加对应的Action类和view的映射关系。

## 具体实现 -- [go-back-to-top](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#hello-world-demo---使用struts-2-框架)

### Step 1

在src/main/java创建 ```MessageStore```类:

**MessageStore.java**

```java
package com.rovo98.struts.helloworld.model;

public class MessageStore {
    private String message;

    public MessageStore() {
        message = "Hello Struts User";
    }
    public MessageStore(String msg) {
        this.message = msg;
    }
    public String getMessage() {
        return message;
    }
}
```

**[notice]**: 这里所写的Javabean必须要有public 的getter方法，struts 2
在相应的view视图中才能获取到bean的数据内容。

### Step 2

创建```HelloWorldAction.java```Action类
Action类相当于MVC模式中的controller. Action类相应用户发起的请求，
类中可能有一个或多个方法会被执行，并返回一个String结果。根据得到的结果
，将相应的资源返回给用户(这个例子，对应的是HelloWorld.jsp)

**HelloWorldAction.java**

```java
package com.rovo98.struts.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rovo98.struts.helloworld.model.MessageStore;

public class HelloWorldAction extends ActionSupport {
    private MessageStore messageStore;

    public String execute() {
        messageStore = new MessageStore();
        return SUCCESS;
    }

    public MessageStore getMessageStore() {
        return messageStore;
    }
}
```

**[notice]**: Struts 2 框架会创建```HelloWorldAction```类的一个对象，并调用对象的
```execute()```方法来响应用户的操作(点击超链接传递相应URL给一个Servlet容器).

### Step 3 -- [go back to top](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#hello-world-demo---使用struts-2-框架)

创建一个用来显示```MessageStore```类中信息的视图页面 ```HelloWorld.jsp```

**HelloWorld.jsp**

```jsp
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
    <h2><s:property value="messageStore.message" /></h2>
</body>
</html>

```

**[notice]**: 这个页面使用了Struts 2的标签库的一个数据标签来显示数据：
```messageStore```是调用了Action类中的```getMessageStore()```方法获得
```MessageStore```类的对象，```.message```是调用对象的```getMessage()```方法。
这样的话，对象的成员变量```message```中的数据就会被以String的形式显示在页面中。

### Step 4

在 ```struts.xml```文件添加相关的Struts配置信息，我们需要通过一个映射关系
将要响应的URL和负责处理的Action（controller）以及视图（HelloWorld.jsp)绑定联系起来。

**struts.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE struts PUBLIC
        "-//Apache Software Foundation//DTD Struts Configuration 2.5//EN"
        "http://struts.apache.org/dtds/struts-2.5.dtd">

<struts>
    <constant name="struts.devMode" value="true" />

    <package name="basicstruts2" extends="struts-default">
        <action name="index">
            <result>/index.jsp</result>
        </action>
        <action name="hello" class="com.rovo98.struts.helloworld.action.HelloWorldAction"
        method="execute">
            <result name="success">/HelloWorld.jsp</result>
        </action>
    </package>
</struts>
```

### Step 5 -- [go back to top](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#hello-world-demo---使用struts-2-框架)

在Web项目中的index.jsp页面里添加一个```Action URL```, 让用户可以通过点这个链接
来告诉Struts 2框架执行```HelloWorldAction```的```execute()```方法，并呈现```HelloWorld.jsp```
视图。

**index.jsp**

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Basic Struts 2 Application - Welcome</title>
</head>
<body>
    <h1>Welcome to Struts 2!</h1>
    <p><a href="<s:url action='hello'/>">Hello, World!</a></p>
</body>
</html>
```
**[notice]**: Struts 的 url 标签创建了包含hello Action的URL，而这个
hello Action 映射着```HelloWorldAction```类和它的```execute()```方法，用户点击
URL链接后，Struts 框架执行```execute()```方法，方法返回success,相应的视图(HelloWorld.jsp)
会被呈现。

## 工作原理

我们的浏览器发送一个请求(http://localhost:8080/helloworld/hello.action)

1. 容器(Tomcat, ..)从Web服务器接收对资源hello.action的请求，根据web.xml的加载设置，所有的请求都会被
Struts 2框架的核心过滤器所过滤；这当然包括*.action请求，```StrutsPrepareAndExecuteFilter```
是Struts 2框架的入口;
2. 框架查找名为'hello'的Action映射，并发现该映射对应于类```HelloWorldAction```,框架实例化这个Action,并执行
```execute()```方法;
3. ```execute()```创建```MessageStore```对象并返回success,框架检查Action的映射并查看要加载的页面,然后告诉
容器渲染页面(HelloWorld.jsp)作为请求的响应;
4. 当处理HelloWorld.jsp页面时，相应的标签调用Action中的getter方法获取对象，并调用对象的getter方法来获取需要的数据，
标签将消息属性的值合并到响应中；
5. 纯HTML响应被发送回浏览器。


### What to Remember -- [go back to top](https://github.com/rovo98/java-learning/blob/master/struts2-learning/notes/01_HelloWorldUsingStruts2.md#hello-world-demo---使用struts-2-框架)

框架使用Action类来处理HTML表单和其他请求，Action类返回一个结果，如：success, input, error
，基于从struts.xml中加载的映射关系，给定的结果名称可以选择一个页面，另一个Action或者一些其他网络资源（图像，PDF等）。

当呈现页面时，通常会包含Action提供的动态数据，为了便于显示动态数据，框架提供了一组可以与
HTML标记一起使用的标签来创建页面。
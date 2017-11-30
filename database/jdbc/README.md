# jdbc-传统数据库连接的使用

### 1.数据库连接的获取和释放
#### 数据库连接的获取
- 动态加载目标数据库的连接驱动
- 配置连接数据库所需的参数
  - 数据库连接地址
  - 数据库用户名
  - 数据库用户密码
- 通过DriverManager获取数据库连接
#### 数据库连接的释放
- 直接调用Connection对象的close()方法释放资源

###### 具体实现：[点击查看](https://github.com/rovo98/java-learning/blob/master/database/jdbc/baseDaoImpl.java)
---------------------------------------------------------------------------------

### 2.数据库相关操作
- 操作过程
  - 根据获取到的数据库连接对象conn,生成statement或preparedStatement对象
  - statement(直接执行sql语句)和preparedStatement(执行预处理sql语句)

 - statement操作结果分有结果集返回和无结果集返回两种
 - 相对应的jdbc提供的两个方法
 - executeUpdate()和executeQuery()方法
#### 有结果集返回的操作
- 查询操作
#### 无结果集返回的操作
- 数据增加操作
- 数据修改操作
- 数据删除操作

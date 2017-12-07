---
author: rovo98
---

# C3p0数据库连接池的使用

### 为什么使用数据库连接池

  一般我们在项目中操作数据库时，都是需要操作数据库时就创建一个连接，操作完成后释放连接。因为jdbc没有保持连接的能力，一旦操作一定时间没有使用(大约几百毫秒),连接会自动释放掉。而新建连接大概需要140毫秒左右的时间，所以耗费时间比较多。而若使用数据库连接池来管理数据库连接，随取随用，该过程所耗费的时间将大大减少。这在实际开发中有很大的意义。


### 连接池原理

  连接池的基本思想是在系统初始化时，将**数据库连接作为对象存储在内存中**，当用户需要访问数据库时，**并非创建新的连接**，而是从数据库连接池中取出一个已建立的空闲连接对象。使用结束后，用户也**并非将连接关闭**，而是将连接放回连接池中，以便下次请求使用。而数据库连接的建立、断开都由连接池自身来管理。可以通过设置连接池的参数来控制连接池的初始化。

### C3p0数据库连接池介绍

  C3p0是一个开源的JDBC连接池，它实现了数据源和JNDI绑定，支持JDBC3规范和JDBC2的标准扩展。目前使用它的开源项目有Hibernate, Spring等。C3p0数据源在项目开发中使用比较多。

### 一、C3p0Utils: 负责从连接池中获取一个连接、放回一个连接

  主要是C3p0连接池的使用。
- 1：下载C3p0连接池所需要的jar包： https://sourceforge.net/projects/c3p0/files/latest/download?source=files ,解压出来后得到导入相关jar包
  - c3p0-0.9.5.2.jar和mchange-commons-java-0.2.11.jar
  - 如果是Oracle数据库需要导入c3p0-oracle-thin-extras-0.9.5.2.jar

- 2：对C3p0连接池进行配置

  主要包括：初始化连接池时建立的多少个连接(initialPoolSize)、连接池最少多少个连接(minPoolSize)、最多容纳多少连接(maxPoolSize)、每个连接的生存时间(maxIdleTime)、连接池能同时允许多少个操作进行(maxStatements),以及具体的数据库连接池配置: 数据库驱动(driverClass)、数据库连接URL(jdbcUrl)、数据库登录名(user)、数据库密码(password)等。一个数据库的连接池配置用一个 
  ```xml
  <name-config name="标识"></name-config>
  ```

  节点来定义。在C3p0Utils中创建连接池时把"标识"作为连接池的构造函数参数传入，C3p0在配置文件中找到该标识对应的配置信息，按照对应节点的配置信息来创建连接池。
  **[注]: c3p0-config.xml必须被创建在src目录下，且必须是这个文件名。**
  文件具体配置: [查看](https://github.com/rovo98/java-learning/blob/master/database/c3p0/c3p0-config.xml)

- 3.创建C3p0Utils类，定义获取connection、释放connection的方法
  具体定义: [查看](https://github.com/rovo98/java-learning/blob/master/database/c3p0/C3p0Utils.java)

### 二、创建DBUtil_BO类来封装一次数据库操作

  BO类中主要封装了一个数据库连接、一个PreparedStatement、一个结果集。在dao层的一次数据库操作可封装在这个对象里，直接把这个对象传给数据库操作类执行数据库操作即可。操作完成的结果集也封装在这个对象里面，同时，在该类中也封装了数据库的事物处理操作，setAutoCommit()、commit()、rollback()。具体实现: [查看](https://github.com/rovo98/java-learning/blob/master/database/c3p0/DBUtil_BO.java)

### 三、创建DBUtils数据库操作类进行具体的数据库从操作

  数据库操作类主要负责执行数据库操作封装类bo对象的操作以及释放连接回连接池，并把结果封装到bo对象中的rs对象中。
  具体实现: [查看](https://github.com/rovo98/java-learning/blob/master/database/c3p0/DBUtils.java)


### 四、在项目中具体使用连接池步骤

- 1.创建初始化数据库连接池
- 2.使用C3p0Utils工具类获取连接conn对象封装在bo对象中
- 3.定义数据库操作sql语句，并用bo对象的preparedStatement预处理
- 4.使用DBUtils执行数据库操作，分有结果集返回和无结果集返回两种
- 5.bo对象释放连接，返回连接池中，以便下一次请求获取连接。

实例 ：这是定义在dao层中的一些方法实现
已有的javabean定义如下:
```java
package com.rovo98.C3p0PoolTest.model ;
/**
* javabean示例
* @author rovo98
*/
public class User {
  private int userId  ;
  private String userName  ;
  private String userPW  ;
  
  public void setUserId(int userId) {
    this.userId = userId ;
  }
  public void getUserId() {
  	return this.userId ;
  }
  public void setUserName(String userName) {
  	this.userName = userName ;
  }
  public void getUserName() {
  	return this.userName ;
  }
  public void setUserPW(String userPW) {
  	this.userPW = userPW ;
  }
  public void getUserPW() {
  	return this.userPW
  }
}
```
dao层某个实现:
```java
package com.rovo98.C3p0PoolTest.dao.impl ;

import com.rovo98.C3p0PoolTest.model.User ;
import com.rovo98.C3p0PoolTest.DBUtils.C3p0Utils ;
import com.rovo98.C3p0PoolTest.DBUtils.DBUtils ;
import com.rovo98.C3p0PoolTest.DBUtils.DBUtil_BO ;
import java.util.List ;

public class userDaoImpl implements userDao {
  DBUtil_BO dbBo = new DBUtil_BO() ;
  
  // 添加用户信息到数据库中
  public boolean addUser(User user) {
    String sql = "INSERT INTO users VALUES(?,?,?)" ;
    dbBo.conn = C3p0Utils.getConnection() ;
    dbBo.setAutoCommit(false) ;
    
    try {
  		  dbBo.pstmt = dbBo.conn.preparedStatement(sql) ;
      	dbBo.pstmt.setInt(user.getUserId()) ;
      	dbBo.pstmt.setString(user.getUserName()) ;
      	dbBo.pstmt.setString(user.getUserPW()) ;
      	
      	DBUtils.executeUpdate() ;
      	return true ;
	  }catch (SQLException e) {
  		e.printStackTrace() ;
      return false ;
    }
    DBUtils.releaseSource(dbBo) ;
    return false ;
  }
  // 获取users表中所有用户
  public List<User> queryAllUsers() {
    String sql = "SELECT * FROM users" ;
    dbBo.conn = C3p0Utils.getConnection() ;
    dbBo.setAutoCommit(false) ;
    List<User> list = null ;
    try {
  		dbBo.pstmt = dbBo.conn.preparedStatement(sql) ;
      	
      	DBUtils.executeQuery(dbBo) ;
      	dbBo.commit() ;
      	list = new ArrayList<User>() ;
      	if (dbBo.rs.next()) {
  		    User user = new User() ;
          	user.setUserId(dbBo.rs.getInt("userId")) ;
          	user.setUserName(dbBo.rs.getString("userName")) ;
          	user.setUserPW(dbBo.rs.getString("userPW")) ;
          	
          	list.add(user) ;
       }
       DBUtils.releaseSource(dbBo) ;
       return list ;
    }catch (SQLException e) {
  		e.printStackTrace() ;
      dbBo.rollback() ;
      DBUtils.releaseSource(dbBo) ;
      return list ;
	}
    DBUtils.releaseSource(dbBo) ;
    return list ;
  }
}
```


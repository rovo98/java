import java.sql.Connection;

/**
 * baseDao接口
 * @author rovo98
 *
 */
public interface baseDao {

	Connection getConnection() ; // 获取数据库连接
	void closeConnection() ;     // 关闭数据库连接
}

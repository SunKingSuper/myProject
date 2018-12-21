package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Database {

	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/MaterialManagement";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "wangming";
	static final String PASS = "2016015338";

	public static List use(String sql, RSHandle rsHandle) {
		List list = null;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			prepareStatement = conn.prepareStatement(sql);
			System.out.println(prepareStatement);
			ResultSet rs = null;
			// 展开结果集数据库
			if (rsHandle != null) {
				rs = prepareStatement.executeQuery();
				list = rsHandle.handle(rs);
			} else {
				prepareStatement.execute();
			}
			// 完成后关闭
			if (rs != null) {
				rs.close();
			}
			prepareStatement.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (prepareStatement != null)
					prepareStatement.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}
}

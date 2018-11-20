package Model;

import java.sql.*;
import Log.TheLog;

/**
 * 
 * @author KingSun
 * @version 1.0
 * 这个类是用来连接mysql的，然后每次执行一次sql就得重连一次mysql
 */
public class Database {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/KingSun_Hotel";
	
	private static final String USER = "Dev";
	private static final String PASS = "Dev2018";
	
	public static ResultSet execute(String sql) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// 注册JDBC驱动
			Class.forName(JDBC_DRIVER);
			TheLog.info("连接数据系统...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			TheLog.info("statement对象实例化");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);			
			// resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException se) {
			// TODO: 处理JDBC错误
			TheLog.warn(se.toString());
			se.printStackTrace();
		} catch (Exception e) {
			// TODO: 处理 Class.forName 错误
			TheLog.warn(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(statement != null) {
					statement.close();
				}
			} catch (SQLException se2) {
				// TODO: Nothing to do
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				// TODO: handle exception
				TheLog.warn(e2.toString());
				e2.printStackTrace();
			}
		}
		TheLog.info("连接结束");
		return resultSet;
	}
}

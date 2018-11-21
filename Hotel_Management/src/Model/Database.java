package Model;

import java.sql.*;
import Log.TheLog;

/**
 * 
 * @author KingSun
 * @version 1.0
 * Core 将会是唯一持有database的类
 */
public class Database {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/KingSun_Hotel";
	
	private static final String USER = "Dev";
	private static final String PASS = "Dev2018";
	private Connection connection = null;
	private Statement statement = null;
	public Database() {
		connect();
	}
	private void connect() {
		try {
			// 注册JDBC驱动
			Class.forName(JDBC_DRIVER);
			TheLog.info("连接数据系统...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			TheLog.info("statement对象实例化");
			statement = connection.createStatement();
			TheLog.info("数据库连接成功");
		} catch (SQLException se) {
			// TODO: 处理JDBC错误
			TheLog.warn(se.toString());
			se.printStackTrace();
		} catch (Exception e) {
			// TODO: 处理 Class.forName 错误
			TheLog.warn(e.toString());
			e.printStackTrace();
		}
	}
	public ResultSet execute(String sql) {
		ResultSet resultSet = null;
		try {
			statement.executeQuery(sql);
		} catch (Exception e) {
			TheLog.warn(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultSet;
	}
	public void close() {
		try {
			statement.close();
			connection.close();
			TheLog.info("数据库已断开连接");
		} catch (Exception e) {
			TheLog.warn(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}

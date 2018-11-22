package model;

import java.security.spec.DSAGenParameterSpec;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import Log.TheLog;

public class JdbcUtils {
	private static DataSource ds = null;
	static {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			/*String driverClassName = properties.getProperty("driverClassName");
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			Class.forName(driverClassName);*/
			ds = DruidDataSourceFactory.createDataSource(properties);
			TheLog.info("连接数据系统...");
		} catch (Exception e) {
			TheLog.warn(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			TheLog.warn(e.getLocalizedMessage());
			e.printStackTrace();
		}		
		return null;
	}
	public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if(rs != null)rs.close();
		} catch (Exception e) {
			TheLog.warn("resultSet关闭错误");
		} finally {
			try {
				if(st != null)st.close();
			} catch (Exception e1) {
				TheLog.warn("statement关闭错误");
			} finally {
				try {
					if(conn != null)conn.close();
				} catch (Exception e2) {
					TheLog.warn("connection关闭错误");
				}
			}
		}
	}
}

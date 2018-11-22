package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Log.TheLog;
import model.Dao.MyResultSetHandle;

public class JdbcTemplate {
	public static void update(String sql, Object...paras) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			//重点来了
			for(int i = 0;i < paras.length; i++) {
				preparedStatement.setObject(i, paras[i]);//SQL的序号都是从1开始
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			TheLog.warn("Update操作出错");
			e.printStackTrace();
		} finally {
			JdbcUtils.close(connection, preparedStatement, null);
		}
	}
	public static List query(String sql, MyResultSetHandle rsHandle, Object...paras) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List resultList = new ArrayList();
		try {
			//重点来了
			for(int i = 0;i < paras.length; i++) {
				preparedStatement.setObject(i, paras[i]);//SQL的序号都是从1开始
			}
			resultSet = preparedStatement.executeQuery();
			resultList = rsHandle.handle(resultSet);
		} catch (Exception e) {
			TheLog.warn("query操作出错");
			e.printStackTrace();
		} finally {
			JdbcUtils.close(connection, preparedStatement, null);
		}
		return resultList;
	}
}

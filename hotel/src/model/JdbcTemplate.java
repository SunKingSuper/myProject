package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Log.TheLog;
import model.Dao.MyResultSetHandle;

public class JdbcTemplate {
	public static void update(String sql, Object... paras) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// 重点来了
			for (int i = 0; i < paras.length; i++) {
				preparedStatement.setObject(i + 1, paras[i]);// SQL的序号都是从1开始
			}
			preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
			TheLog.info("Success");
		} catch (Exception e) {
			TheLog.warn("Update操作出错");
			TheLog.warn(preparedStatement.toString());
			e.printStackTrace();
		} finally {
			JdbcUtils.close(connection, preparedStatement, null);
		}
	}

	public static List query(String sql, MyResultSetHandle rsHandle, Object... paras) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List resultList = new ArrayList();
		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// 重点来了
			for (int i = 0; i < paras.length; i++) {
				preparedStatement.setObject(i + 1, paras[i]);// SQL的序号都是从1开始
			}
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			resultList = rsHandle.handle(resultSet);
			TheLog.info("执行成功");
			if (resultList.size()<3) {
				System.out.println(resultList);
			}
		} catch (Exception e) {
			TheLog.warn("query操作出错");
			TheLog.warn(preparedStatement.toString());
			e.printStackTrace();
		} finally {
			JdbcUtils.close(connection, preparedStatement, resultSet);
		}
		return resultList;
	}
}

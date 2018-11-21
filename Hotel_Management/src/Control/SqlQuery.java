package Control;

import java.sql.Date;
import java.sql.ResultSet;

import Log.TheLog;

public class SqlQuery {
	public static void User_Infomation(Core core, String UserID) {
		String sql = "SELECT * FROM User WHERE idUser =" + UserID ;
		TheLog.info("查询某人身份信息");
		ResultSet rSet = core.database.execute(sql);//这里只能使用sql包里的Date
		int userID = 0;String passWord = "0";Date login = new Date(1);Date logout = new Date(1);
		try {
			if(rSet.next()) {
				userID = rSet.getInt("idUser");
				passWord = rSet.getString("password");
				login = rSet.getDate("lastLogIn");
				logout = rSet.getDate("lastLogout");
			}
		} catch (Exception e) {
			// TODO: handle exception
			TheLog.warn(e.getLocalizedMessage());
			e.printStackTrace();
		}
		PipeLine.put("aInt", new Integer(userID));
		
	}
}
package Model;

import java.sql.ResultSet;

public class RoomView {
	public static void main(String[] args) {
		String sql = "SELECT roomType, price FROM roomType";
		ResultSet resultSet = Database.execute(sql);
		try {
			while(resultSet.next()) {
				String roomType = resultSet.getString("roomType");
				Float price = resultSet.getFloat("price");
				System.out.println(roomType);
				System.out.println(price);
			}
			resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

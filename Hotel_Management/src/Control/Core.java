package Control;

import Model.Database;
import application.App;

public class Core {
	public Database database;
	public Core() {
		database = new Database();
	}
	public void close() {
		database.close();
	}
	public boolean login(String userID, String password) {
		
		return false;
	}
}

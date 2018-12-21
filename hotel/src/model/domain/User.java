package model.domain;

import java.sql.Timestamp;
import java.util.Date;;

public class User {
	private long idUser;
	private String password;
	private Timestamp lastLogin;
	private Timestamp lastLogout;
	private String role;

	public void setidUser(long id) {
		idUser = id;
	}

	public void setpassword(String pwd) {
		password = pwd;
	}

	public void setlastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setlastLogout(Timestamp lastLogout) {
		this.lastLogout = lastLogout;
	}

	public void setrole(String role) {
		this.role = role;
	}
	
	public long getidUser() {
		return idUser;
	}

	public String getpassword() {
		return password;
	}

	public Timestamp getlastLogin() {
		return lastLogin;
	}

	public Timestamp getlastLogout() {
		return lastLogout;
	}

	public String getrole() {
		return role;
	}
	
	@Override
	public String toString() {
		return String.format("帐号 %d |密码 %s |最后登录时间 %tc |最后退出时间 %tc", idUser, password, new Date(lastLogin.getTime()),
				new Date(lastLogout.getTime()));
	}
}

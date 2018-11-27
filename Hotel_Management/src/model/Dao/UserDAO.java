package model.Dao;

import java.util.List;

import model.domain.User;

//Dao 是用来表示一个对象的CURD操作
public interface UserDAO {
	void save(User user);
	void delete(Long idUser);
	void update(User newUser);
	User get(Long idUser);
	List<User> listAll();
}

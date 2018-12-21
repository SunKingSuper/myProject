package model.Dao;

import java.util.List;

import model.domain.Guest;
import model.domain.Room;

public interface GuestDao {
	void save(Guest guest);
	void add(Guest newGuest); // 只需要身份证号
	void addbyphone(Guest newGuest);
	void delete(Long idGuest);
	void update(Guest newUser);
	Guest get(Long idUser);
	Guest getbyPhonenumber(String phonenumber);
	List<Guest> listAll();
	Guest getByidCard(String idCard);
	List<Guest> listBookGuests(long idOrder);
}
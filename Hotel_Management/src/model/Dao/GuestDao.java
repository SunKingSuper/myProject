package model.Dao;

import java.util.List;

import model.domain.Guest;

public interface GuestDao {
	void save(Guest guest);
	void delete(Long idGuest);
	void update(Guest newUser);
	Guest get(Long idUser);
	List<Guest> listAll();
}
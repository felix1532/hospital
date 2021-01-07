package dao;

import domain.User;

import java.util.List;

public interface UserDao extends Dao<User> {
	List<User> readAll() throws DaoException;
	User find(String login, String password) throws DaoException;
}

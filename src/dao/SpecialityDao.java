package dao;

import domain.Speciality;

import java.util.List;

public interface SpecialityDao extends Dao<Speciality> {
	List<Speciality> readAll() throws DaoException;
}

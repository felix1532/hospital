package dao;

import domain.Doctor;

import java.util.List;

public interface DoctorDao extends Dao<Doctor> {
	List<Doctor> readAll() throws DaoException;
	List<Doctor> readBySpeciality(Long SpecialityId) throws DaoException;
}

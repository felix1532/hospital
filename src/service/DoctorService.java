package service;

import dao.DaoException;
import domain.Doctor;

import java.util.List;

public interface DoctorService {
	List<Doctor> findBySpeciality(Long specialityId) throws DaoException;
	List<Doctor> findAll() throws DaoException;
	Doctor findById(Long id) throws ServiceException;
	void save(Doctor doctor) throws ServiceException;
	void delete(Long id) throws ServiceException;
}

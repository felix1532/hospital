package service;

import dao.DaoException;
import domain.Speciality;

import java.util.List;

public interface SpecialityService {
	List<Speciality> findAll() throws DaoException;
	Speciality findById(Long id) throws ServiceException;
	void save(Speciality speciality) throws ServiceException;
	void delete(Long id) throws ServiceException;
}

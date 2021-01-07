package service;

import dao.DaoException;
import dao.DoctorDao;
import dao.SpecialityDao;
import domain.Doctor;
import domain.Speciality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorServiceImpl extends BaseServiceImpl implements DoctorService {
	private SpecialityDao specialityDao;
	private DoctorDao doctorDao;

	@Override
	public List<Doctor> findBySpeciality(Long specialityId) throws DaoException {
		List<Doctor> doctors = doctorDao.readBySpeciality(specialityId);
		Map<Long, Speciality> specialities = new HashMap<>();
		for(Doctor doctor : doctors) {
			Speciality speciality = doctor.getSpeciality();
			if(speciality != null) {
				Long id = speciality.getId();
				speciality = specialities.get(id);
				if(speciality == null) {
					speciality = specialityDao.read(id);
					specialities.put(id, speciality);
				}
				doctor.setSpeciality(speciality);
			}
		}
		return doctors;
	}

	@Override
	public Doctor findById(Long id) throws ServiceException {
		try {
			return doctorDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public List<Doctor> findAll() throws DaoException {
		return doctorDao.readAll();
	}

	@Override
	public void save(Doctor doctor) throws ServiceException {
		try {
			getTransaction().transactionStart();
			if(doctor.getId() != null) {
				doctorDao.update(doctor);
			} else {
				doctorDao.create(doctor);
			}
			getTransaction().transactionCommit();
		} catch(DaoException e) {
			getTransaction().transactionRollback();
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			getTransaction().transactionStart();
			doctorDao.delete(id);
			getTransaction().transactionCommit();
		} catch(DaoException e) {
			getTransaction().transactionRollback();
			throw new ServiceException(e);
		}
	}

	public void setSpecialityDao(SpecialityDao specialityDao) {
		this.specialityDao = specialityDao;
	}

	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
}

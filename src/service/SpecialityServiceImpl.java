package service;

import dao.DaoException;
import dao.SpecialityDao;
import domain.Speciality;

import java.util.List;

public class SpecialityServiceImpl extends BaseServiceImpl  implements SpecialityService {
	private SpecialityDao specialityDao;

	@Override
	public List<Speciality> findAll() throws DaoException {
		return specialityDao.readAll();
	}

	@Override
	public void save(Speciality speciality) throws ServiceException {
		try {
			getTransaction().transactionStart();
			if(speciality.getId() != null) {
				specialityDao.update(speciality);
			} else {
				specialityDao.create(speciality);
			}
			getTransaction().transactionCommit();
		} catch(DaoException e) {
			getTransaction().transactionRollback();
			throw new ServiceException(e);
		}
	}

	@Override
	public Speciality findById(Long id) throws ServiceException {
		try {
			return specialityDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			getTransaction().transactionStart();
			specialityDao.delete(id);
			getTransaction().transactionCommit();
		} catch(DaoException e) {
			getTransaction().transactionRollback();
			throw new ServiceException(e);
		}
	}


	public void setSpecialityDao(SpecialityDao specialityDao) {
		this.specialityDao = specialityDao;
	}
}

package test.dao;

import dao.DaoException;
import dao.DoctorDao;
import dao.fake.DoctorDaoFakeImpl;
import domain.Doctor;
import test.Utility;

import java.util.List;

public class DoctorDaoReadTest {
	private static void output(DoctorDao doctorDao) throws DaoException {
		List<Doctor> doctors = doctorDao.readAll();
		System.out.println("Список всех докторов");
		System.out.println("===================");
		for(Doctor doctor : doctors) {
			System.out.println(Utility.toString(doctor));

		}
		System.out.println();
	}
	
	public static void main(String[] args) throws DaoException {
		DoctorDao doctorDao = new DoctorDaoFakeImpl();
		output(doctorDao);
//		List<Doctor> doctors = doctorDao.readAll();
//		System.out.println(Utility.toString(doctorDao.read((long)doctors.size())));
	}
}

package test.dao;

import dao.DaoException;
import dao.DoctorDao;
import dao.fake.DoctorDaoFakeImpl;
import domain.Doctor;
import domain.Speciality;
import test.Utility;

import java.util.List;

public class DoctorDaoUpdateTest {
	private static void output(DoctorDao doctorDao) throws DaoException {
		List<Doctor> doctors = doctorDao.readAll();
		System.out.println("Список всех враечей");
		System.out.println("===================");
		for(Doctor doctor : doctors) {
			System.out.println(Utility.toString(doctor));
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws DaoException {
		DoctorDao doctorDao = new DoctorDaoFakeImpl();
		output(doctorDao);

		Doctor doctor = new Doctor();
		doctor.setId(1L);
		doctor.setFirstName("Vitalyy");
		doctor.setLastName("Prutkov");
		doctor.setPatronymic("Loles");
//		doctor.setBirthdayDate("20.11.1999");
//		doctor.setEmploymentDate("12.07.2010");
		doctor.setSpeciality(new Speciality());
		doctor.getSpeciality().setName("spec");
		doctor.getSpeciality().setDoctorsCount(10);
		doctor.getSpeciality().setIsNarrowSpecialist(true);
		doctor.setLotNumber(6);
		doctor.getSpeciality().setId(2L);
		doctor.setSalary(950.00);
		doctorDao.update(doctor);
		
		output(doctorDao);
	}
}

package test.service;

import dao.DaoException;
import domain.Doctor;
import ioc.IoCConfigurer;
import ioc.IoCContainer;
import ioc.IoCException;
import service.DoctorService;
import test.Utility;

import java.util.List;

public class DoctorServiceFindBySpecialityTest {
	public static void main(String[] args) throws IoCException, DaoException {
		IoCConfigurer.configure();
		IoCContainer ioc = new IoCContainer();
		DoctorService doctorService = ioc.get(DoctorService.class);
		List<Doctor> doctors = doctorService.findBySpeciality(2L);
		System.out.printf("Список всех докторов специальности с идентификатором [%d]\n", 1L);
		System.out.println("================================================");
		for(Doctor doctor : doctors) {
			System.out.println(Utility.toString(doctor));
		}
		System.out.println();
	}
}

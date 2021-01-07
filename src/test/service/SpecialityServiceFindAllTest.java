package test.service;

import dao.DaoException;
import domain.Speciality;
import ioc.IoCConfigurer;
import ioc.IoCContainer;
import ioc.IoCException;
import service.SpecialityService;
import test.Utility;

import java.util.List;

public class SpecialityServiceFindAllTest {
	public static void main(String[] args) throws IoCException, DaoException {
		IoCConfigurer.configure();
		IoCContainer ioc = new IoCContainer();
		SpecialityService specialityService = ioc.get(SpecialityService.class);
		List<Speciality> specialities = specialityService.findAll();
		System.out.println("Список всех специальностей");
		System.out.println("===================");
		for(Speciality speciality : specialities) {
			System.out.println(Utility.toString(speciality));
		}
	}
}

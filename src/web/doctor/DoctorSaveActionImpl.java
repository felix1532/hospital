package web.doctor;

import domain.Doctor;
import service.DoctorService;
import service.ServiceException;
import service.SpecialityService;
import web.Action;
import web.ActionResult;
import web.ActionResultType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DoctorSaveActionImpl implements Action {
	private DoctorService doctorService;
	private SpecialityService specialityService;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Doctor doctor = getDoctor(req);
			doctorService.save(doctor);
			
			String returnUrl = req.getParameter("returnUrl");
			if(returnUrl != null)
				return new ActionResult(returnUrl);
		   	return new ActionResult("/doctor/index.html?specialityId=" + doctor.getSpeciality().getId());
		} catch(ServiceException e) {
			throw new ServletException(e);
		} catch(IllegalArgumentException e) {
			req.setAttribute("message", e.getMessage());
			return new ActionResult("/error", ActionResultType.FORWARD);
		}
	}

	public void setDoctorService(DoctorService service) {
		this.doctorService = service;
	}
	
	public void setSpecialityService(SpecialityService service) {
		this.specialityService = service;
	}

	private Doctor getDoctor(HttpServletRequest req) throws ServiceException {
		Doctor doctor = new Doctor();
		String errorMessage = new String();
		
		try {
			doctor.setId(Long.parseLong(req.getParameter("id")));
		} catch(NumberFormatException e) {}

		doctor.setSpeciality(specialityService.findById(Long.parseLong(req.getParameter("specialityId"))));
		doctor.setFirstName(req.getParameter("firstName"));
		doctor.setLastName(req.getParameter("lastName"));
		doctor.setPatronymic(req.getParameter("patronymic"));
		doctor.setLotNumber(Integer.valueOf(req.getParameter("lotNumber")));


		// Дата рождения
		String dateOfBirthdayString = req.getParameter("birthdayDate");
		if(dateOfBirthdayString.isEmpty()) errorMessage += "Не указана дата рождения<br>";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateOfBirthday = null;
		try {
			if(!dateOfBirthdayString.isEmpty())
				dateOfBirthday = format.parse(dateOfBirthdayString);
		} catch (ParseException e) {
			errorMessage += "Неверная дата. Правильный формат даты: YYYY-MM-DD<br>";
		}

		if(dateOfBirthday != null)
			doctor.setBirthdayDate(new Date(dateOfBirthday.getTime())); // преобразуем util.Date в sql.Date



		// Дата приема на работу
		String employmentDateString = req.getParameter("employmentDate");
		if(employmentDateString.isEmpty()) errorMessage += "Не указана дата приема на работу<br>";
		java.util.Date employmentDate = null;
		try {
			if(!employmentDateString.isEmpty())
				employmentDate = format.parse(employmentDateString);
		} catch (ParseException e) {
			errorMessage += "Неверная дата. Правильный формат даты: YYYY-MM-DD<br>";
		}

		if(employmentDate != null)
			doctor.setEmploymentDate(new Date(employmentDate.getTime())); // преобразуем util.Date в sql.Date

		java.util.Date dateTwo = new java.util.Date();
		long difference = dateTwo.getTime() - dateOfBirthday.getTime();
		int days = (int) (difference / ( 24 * 60 * 60 * 1000));
		if(days < 7300){
			errorMessage += "Возраст специалиста менее 20 лет !";
		}

		if(doctor.getSpeciality().getIsNarrowSpecialist() && doctor.getLotNumber() != 0){
			errorMessage += "Врач является узким специалистом, поэтому ему нельзя установить поле Участок !";
		}
		doctor.setSalary(Double.valueOf(req.getParameter("salary")));

		if(doctor.getSpeciality() == null)
		errorMessage += "Не выбрано отделение<br>";
		if(doctor.getFirstName() == null || doctor.getFirstName().isEmpty())
			errorMessage += "Не заполнено поле \"Имя\"<br>";
		if(doctor.getLastName() == null || doctor.getLastName().isEmpty())
			errorMessage += "Не заполнено поле \"Фамилия\"<br>";
		if(doctor.getPatronymic() == null || doctor.getPatronymic().isEmpty())
			errorMessage += "Не заполнено поле \"Отчество\"<br>";
		if(doctor.getBirthdayDate() == null )
			errorMessage += "Не заполнено поле \"Дата рождения\"<br>";
		if(doctor.getLotNumber() == null )
			errorMessage += "Не заполнено поле \"Номер участка\"<br>";
		if(doctor.getSalary() == null )
			errorMessage += "Не заполнено поле \"Заработная плата\"<br>";


        if(errorMessage.length() != 0)
			throw new IllegalArgumentException(errorMessage);
        
        return doctor;
	}
}

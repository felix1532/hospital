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


		// ���� ��������
		String dateOfBirthdayString = req.getParameter("birthdayDate");
		if(dateOfBirthdayString.isEmpty()) errorMessage += "�� ������� ���� ��������<br>";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateOfBirthday = null;
		try {
			if(!dateOfBirthdayString.isEmpty())
				dateOfBirthday = format.parse(dateOfBirthdayString);
		} catch (ParseException e) {
			errorMessage += "�������� ����. ���������� ������ ����: YYYY-MM-DD<br>";
		}

		if(dateOfBirthday != null)
			doctor.setBirthdayDate(new Date(dateOfBirthday.getTime())); // ����������� util.Date � sql.Date



		// ���� ������ �� ������
		String employmentDateString = req.getParameter("employmentDate");
		if(employmentDateString.isEmpty()) errorMessage += "�� ������� ���� ������ �� ������<br>";
		java.util.Date employmentDate = null;
		try {
			if(!employmentDateString.isEmpty())
				employmentDate = format.parse(employmentDateString);
		} catch (ParseException e) {
			errorMessage += "�������� ����. ���������� ������ ����: YYYY-MM-DD<br>";
		}

		if(employmentDate != null)
			doctor.setEmploymentDate(new Date(employmentDate.getTime())); // ����������� util.Date � sql.Date

		java.util.Date dateTwo = new java.util.Date();
		long difference = dateTwo.getTime() - dateOfBirthday.getTime();
		int days = (int) (difference / ( 24 * 60 * 60 * 1000));
		if(days < 7300){
			errorMessage += "������� ����������� ����� 20 ��� !";
		}

		if(doctor.getSpeciality().getIsNarrowSpecialist() && doctor.getLotNumber() != 0){
			errorMessage += "���� �������� ����� ������������, ������� ��� ������ ���������� ���� ������� !";
		}
		doctor.setSalary(Double.valueOf(req.getParameter("salary")));

		if(doctor.getSpeciality() == null)
		errorMessage += "�� ������� ���������<br>";
		if(doctor.getFirstName() == null || doctor.getFirstName().isEmpty())
			errorMessage += "�� ��������� ���� \"���\"<br>";
		if(doctor.getLastName() == null || doctor.getLastName().isEmpty())
			errorMessage += "�� ��������� ���� \"�������\"<br>";
		if(doctor.getPatronymic() == null || doctor.getPatronymic().isEmpty())
			errorMessage += "�� ��������� ���� \"��������\"<br>";
		if(doctor.getBirthdayDate() == null )
			errorMessage += "�� ��������� ���� \"���� ��������\"<br>";
		if(doctor.getLotNumber() == null )
			errorMessage += "�� ��������� ���� \"����� �������\"<br>";
		if(doctor.getSalary() == null )
			errorMessage += "�� ��������� ���� \"���������� �����\"<br>";


        if(errorMessage.length() != 0)
			throw new IllegalArgumentException(errorMessage);
        
        return doctor;
	}
}

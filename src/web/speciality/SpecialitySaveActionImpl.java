package web.speciality;

import dao.DaoException;
import domain.Doctor;
import domain.Speciality;
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
import java.util.List;

public class SpecialitySaveActionImpl implements Action {
	private SpecialityService service;
	private DoctorService service1;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Speciality speciality = getSpeciality(req);
			try {
				service.save(speciality);
				return new ActionResult("/speciality/index.html");
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		} catch(IllegalArgumentException | DaoException | ServiceException e) {
			req.setAttribute("message", e.getMessage());
			return new ActionResult("/error", ActionResultType.FORWARD);
		}
	}

	public void setSpecialityService(SpecialityService service) {
		this.service = service;
	}

	public void setDoctorService(DoctorService service) {
		this.service1 = service;
	}

	private Speciality getSpeciality(HttpServletRequest req) throws DaoException, ServiceException {
		Speciality speciality = new Speciality();
		String errorMessage = new String();
		
		try {
			speciality.setId(Long.parseLong(req.getParameter("id")));
		} catch(NumberFormatException e) {}
		speciality.setName(req.getParameter("name"));
		speciality.setDoctorsCount(Integer.valueOf(req.getParameter("doctorsCount")));
		speciality.setIsNarrowSpecialist(req.getParameter("isNarrow") != null);
		speciality.setSalaryExpenses(Double.valueOf(req.getParameter("salaryExpenses")));
		speciality.setWageRate(Double.valueOf(req.getParameter("wageRate")));

		if(speciality.getIsNarrowSpecialist()){
			List<Doctor> doctorList = service1.findBySpeciality(speciality.getId());
			for(Doctor doctor : doctorList){
				doctor.setLotNumber(0);
				service1.save(doctor);
			}
		}
        
		if(speciality.getName() == null || speciality.getName().isEmpty())
			errorMessage += "Не заполнено поле \"Название\"<br>";
		if(speciality.getDoctorsCount() == null)
			errorMessage += "Не заполнено поле \"Количество врачей\"<br>";
		if(speciality.getIsNarrowSpecialist() == null)
			errorMessage += "Не заполнено поле \"Узкий ли специалист\"<br>";
		if(speciality.getSalaryExpenses() == null)
			errorMessage += "Не заполнено поле \"Затраты на зарплату\"<br>";
		if(speciality.getWageRate() == null)
			errorMessage += "Не заполнено поле \"Ставка\"<br>";
        
        if(errorMessage.length() != 0)
			throw new IllegalArgumentException(errorMessage);
		return speciality;
	}
}

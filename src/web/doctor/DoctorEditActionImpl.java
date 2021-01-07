package web.doctor;

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

public class DoctorEditActionImpl implements Action {
	private DoctorService doctorService;
	private SpecialityService specialityService;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = null;
		Long specialityId = null;
		try {
			id = Long.parseLong(req.getParameter("id"));
		} catch(NumberFormatException e) {}
		try {
			specialityId = Long.parseLong(req.getParameter("specialityId"));
		} catch(NumberFormatException e) {}
		
		try {		
			// Редактирование отделения
			if(id != null) {
				List<Speciality> specialities = specialityService.findAll();
				req.setAttribute("specialities", specialities);
				
				Doctor doctor = doctorService.findById(id);
				if(doctor == null) {
					String message = "Не найден доктор с таким id";
					req.setAttribute("message", message);
					return new ActionResult("/error", ActionResultType.FORWARD);
				}
				req.setAttribute("doctor", doctor);
			}
			// Если добавление то передается id отделения
			else if (specialityId != null) {
				Speciality speciality = specialityService.findById(specialityId);
				if(speciality == null) {
					String message = "Не найдено отделение с таким id";
					req.setAttribute("message", message);
					return new ActionResult("/error", ActionResultType.FORWARD);
				}
				req.setAttribute("speciality", speciality);
			}
		} catch(ServiceException | DaoException e) {
			throw new ServletException(e);
		}
		return null;
	}

	public void setDoctorService(DoctorService service) {
		this.doctorService = service;
	}
	
	public void setSpecialityService(SpecialityService service) {
		this.specialityService = service;
	}
}

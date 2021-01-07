package web.speciality;

import domain.Speciality;
import service.ServiceException;
import service.SpecialityService;
import web.Action;
import web.ActionResult;
import web.ActionResultType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpecialityEditActionImpl implements Action {
	private SpecialityService service;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = null;
		try {
			id = Long.parseLong(req.getParameter("id"));
		} catch(NumberFormatException e) {}
		
		try {
			if(id != null) {
				Speciality speciality = service.findById(id);
				if(speciality == null) {
					String message = "Не найдена специальность с таким id";
					req.setAttribute("message", message);
					return new ActionResult("/error", ActionResultType.FORWARD);
				}
				req.setAttribute("speciality", speciality);
			}
		} catch(ServiceException e) {
			throw new ServletException(e);
		}
		return null;
	}

	public void setSpecialityService(SpecialityService service) {
		this.service = service;
	}
}

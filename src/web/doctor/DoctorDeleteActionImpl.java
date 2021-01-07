package web.doctor;

import service.DoctorService;
import service.ServiceException;
import web.Action;
import web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class DoctorDeleteActionImpl implements Action {
	private DoctorService service;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String specialityId = req.getParameter("specialityId");
		if(req.getParameterValues("id") == null) {
			if(specialityId != null)
	    		return new ActionResult("/doctor/index.html?specialityId=" + URLEncoder.encode(specialityId, "UTF-8"));
			return new ActionResult("/doctor/index.html");
		}
		
		for(String idStr : req.getParameterValues("id")) {
			Long id = null;
			try {
				id = Long.parseLong(idStr);
				try {
					service.delete(id);
				} catch(ServiceException e) {
					throw new ServletException(e);
				}
			} catch(NumberFormatException e) {};
		}
		
    	if(specialityId != null)
    		return new ActionResult("/doctor/index.html?specialityId=" + URLEncoder.encode(specialityId, "UTF-8"));
		return new ActionResult("/doctor/index.html");
	}

	public void setDoctorService(DoctorService service) {
		this.service = service;
	}
}

package web.speciality;

import dao.DaoException;
import service.SpecialityService;
import web.Action;
import web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpecialityIndexActionImpl implements Action {
	private SpecialityService service;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			String sortStateStr = req.getParameter("sortState");
//			FacultySortState sortState = null;
//			if(sortStateStr != null) {
//				try {
//					sortState = FacultySortState.valueOf(sortStateStr);
//				} catch (IllegalArgumentException e) {};
//			}
//
//			req.setAttribute("titleSort", sortState == FacultySortState.TitleAsc ? FacultySortState.TitleDesc : FacultySortState.TitleAsc);
//			req.setAttribute("fullNameSort", sortState == FacultySortState.FullNameAsc ? FacultySortState.FullNameDesc : FacultySortState.FullNameAsc);
//			req.setAttribute("phoneNumberSort", sortState == FacultySortState.PhoneNumberAsc ? FacultySortState.PhoneNumberDesc : FacultySortState.PhoneNumberAsc);
//			req.setAttribute("dekanatCabinetSort", sortState == FacultySortState.DekanatCabinetAsc ? FacultySortState.DekanatCabinetDesc : FacultySortState.DekanatCabinetAsc);
//			req.setAttribute("studentsCountSort", sortState == FacultySortState.StudentsCountAsc ? FacultySortState.StudentsCountDesc : FacultySortState.StudentsCountAsc);
//			req.setAttribute("dateOfCreateSort", sortState == FacultySortState.DateOfCreateAsc ? FacultySortState.DateOfCreateDesc : FacultySortState.DateOfCreateAsc);
//			req.setAttribute("currentSort", null);
//
//			List<Faculty> faculties;
//			if(sortState != null) {
//				req.setAttribute("currentSort", sortState);
//				faculties = service.findAll(sortState);
//			}
//			else
//				faculties = service.findAll(null);
			req.setAttribute("specialities", service.findAll());
			return null;
		} catch(DaoException e) {
			throw new ServletException(e);
		}
	}

	public void setSpecialityService(SpecialityService service) {
		this.service = service;
	}
}

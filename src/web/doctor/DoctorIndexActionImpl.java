package web.doctor;

import dao.DaoException;
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

//import util.ChairSortState;

public class DoctorIndexActionImpl implements Action {
	private SpecialityService specialityService;
	private DoctorService doctorService;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long specialityId = null;
		try {
			specialityId = Long.parseLong(req.getParameter("specialityId"));
		} catch(NumberFormatException e) {
			req.setAttribute("message", "Не указан id отделения");
			return new ActionResult("/error", ActionResultType.FORWARD);
		}
			
		try {
			// Получаем специальность
			Speciality speciality = specialityService.findById(specialityId);
			if(speciality == null) {
				req.setAttribute("message", "Не найдено отделение с таким id");
				return new ActionResult("/error", ActionResultType.FORWARD);
			}
			
			req.setAttribute("speciality", speciality);
			// В select добавляем специальности
			req.setAttribute("specialities", specialityService.findAll());
			
//			String sortStateStr = req.getParameter("sortState");
//			ChairSortState sortState = null;
//			if(sortStateStr != null) {
//				try {
//					sortState = ChairSortState.valueOf(sortStateStr);
//				} catch (IllegalArgumentException e) {};
//			}
//
//			req.setAttribute("titleSort", sortState == ChairSortState.TitleAsc ? ChairSortState.TitleDesc : ChairSortState.TitleAsc);
//			req.setAttribute("fullNameSort", sortState == ChairSortState.FullNameAsc ? ChairSortState.FullNameDesc : ChairSortState.FullNameAsc);
//			req.setAttribute("phoneNumberSort", sortState == ChairSortState.PhoneNumberAsc ? ChairSortState.PhoneNumberDesc : ChairSortState.PhoneNumberAsc);
//			req.setAttribute("cabinetNumberSort", sortState == ChairSortState.CabinetNumberAsc ? ChairSortState.CabinetNumberDesc : ChairSortState.CabinetNumberAsc);
//			req.setAttribute("teachersCountSort", sortState == ChairSortState.TeachersCountAsc ? ChairSortState.TeachersCountDesc : ChairSortState.TeachersCountAsc);
//			req.setAttribute("releaseSort", sortState == ChairSortState.ReleaseAsc ? ChairSortState.ReleaseDesc : ChairSortState.ReleaseAsc);
//			req.setAttribute("dateOfCreateSort", sortState == ChairSortState.DateOfCreateAsc ? ChairSortState.DateOfCreateDesc : ChairSortState.DateOfCreateAsc);
//			req.setAttribute("currentSort", null);
//
//			List<Doctor> doctors;
//			if(sortState != null) {
//				req.setAttribute("currentSort", sortState);
//				doctors = doctorService.findBySpeciality(speciality.getId());
//			}
//			else
//				doctors = doctorService.findBySpeciality(speciality.getId());
			req.setAttribute("doctors", doctorService.findBySpeciality(speciality.getId()));
			return null;
		} catch(ServiceException | DaoException e) {
			throw new ServletException(e);
		}
	}

	public void setDoctorService(DoctorService service) {
		this.doctorService = service;
	}
	
	public void setSpecialityService(SpecialityService service) {this.specialityService = service;	}
}

package services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Service;
import model.UserClient;
import model.UserProfessional;
import sql.ServiceSQL;

public class SService implements Logic {

	public static Service searchService(int service_id) {
		for (Service s : Logic.arService) {
			if (s.getService_id() == service_id)
				return s;
		}

		return null;
	}

	public static ArrayList<Service> loadServiceInformation(HttpServletRequest req) {

		return Logic.arService;
	}

	public static String addService(HttpServletRequest req, int service_id) {// Apenas utilizado se o user for
		// professional
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");

		for (int i = 0; i < Logic.arUserProfessional.size(); i++) {
			if (Logic.arUserProfessional.get(i).getUser_id() == user_id) {
				Logic.arUserProfessional.get(i).setArService(SService.searchServiceId(service_id));
			}
		}
		return "/index.jsp";
	}

	public static ArrayList<Service> loadServicesUser(HttpServletRequest req) {
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");

		for (UserProfessional uP : Logic.arUserProfessional) {
			if (uP.getUser_id() == user_id) {
				return uP.getArService();

			}
		}
		return null;
	}
	
	public String sendServices(HttpServletRequest req) {
		int user_service_id = Integer.parseInt(req.getParameter("user_service_id"));
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");

		for (int i = 0; i < Logic.arUserProfessional.size(); i++) {
			for (int j = 0; j < Logic.arUserProfessional.get(i).getArService().size(); j++) {
				if (Logic.arUserProfessional.get(i).getUser_id() == user_id
						&& Logic.arUserProfessional.get(i).getArService().get(j).getService_id() == user_service_id) {
					req.setAttribute("objectUserService", Logic.arUserProfessional.get(i).getArService().get(j));
					req.setAttribute("editService", 1);
				}
			}
		}
		return null;

	}
	
	public void editServiceUser(HttpServletRequest req) {
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");
		int service_user_id = Integer.parseInt(req.getParameter("service_user_id"));

		for (UserProfessional uP : Logic.arUserProfessional) {
			for (Service s : uP.getArService()) {
				if (uP.getUser_id() == user_id && s.getService_id() == service_user_id) {
					s.setService_name(req.getParameter("serviceUserNameBusiness"));
					s.setService_tiny_description(req.getParameter("tinyDescriptionEdit"));
					s.setService_full_description(req.getParameter("fullDescriptionEdit"));
					s.setService_category(
							SCategory.searchCategory(Integer.parseInt(req.getParameter("serviceSelectEdit"))));

					ServiceSQL.editService(service_user_id, req.getParameter("serviceUserNameBusiness"),
							req.getParameter("tinyDescriptionEdit"), req.getParameter("fullDescriptionEdit"), "",
							SCategory.sendCategoryId(Integer.parseInt(req.getParameter("serviceSelectEdit"))), user_id);
				}
			}
		}
	}
	
	public void deleteService(HttpServletRequest req) {
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");
		int service_user_id = Integer.parseInt(req.getParameter("user_service_id_delete"));

		for (UserProfessional uP : Logic.arUserProfessional) {
			for (Service s : uP.getArService()) {
				if (uP.getUser_id() == user_id && s.getService_id() == service_user_id) {
					uP.getArService().remove(s);
					ServiceSQL.delService(service_user_id);
				}
			}
		}
	}
	
	public static ArrayList<Service> searchServiceId(int service_id) {
		ArrayList<Service> s = new ArrayList<Service>();
		for (int i = 0; i < Logic.arService.size(); i++) {
			if (Logic.arService.get(i).getService_id() == service_id) {
				s.add(Logic.arService.get(i));
				return s;
			}
		}
		return null;
	}

	public static String infoService(HttpServletRequest req) {
		int service_id = Integer.parseInt(req.getParameter("service_id"));
		UserClient userClient;

		for (UserProfessional p : Logic.arUserProfessional) {
			for (Service s : p.getArService()) {
				if (s.getService_id() == service_id) {
					req.setAttribute("infoObjSercive", s);
					req.setAttribute("infoService", 1);
					req.setAttribute("infoOwnerService", userClient = SUser.searchUserClient(p.getUser_id()));
				}
			}
		}

		return "index?pag=services";
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getParameter("action").equals("sendServices")) {
			sendServices(req);
		} else if (req.getParameter("action").equals("deleteService")) {
			deleteService(req);
		} else if (req.getParameter("action").equals("editServiceUser")) {
			editServiceUser(req);
		}
		return "/index.jsp";
	}

}

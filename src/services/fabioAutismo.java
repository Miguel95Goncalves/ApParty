package services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
import model.Service;
import model.Status;
import model.User;
import model.UserClient;
import model.UserProfessional;
import model.UserType;

import services.PageOnLoad;
import sql.ServiceSQL;
import sql.UserClientSQL;
import sql.UserProfessionalSQL;
import sql.UserSQL;

public class fabioAutismo implements Logic {
	private SStatus userStatus = new SStatus();
	private SUserType userType = new SUserType();
	private SService userService = new SService();
	private UserClientSQL userClientSQL = new UserClientSQL();

	public String registUser(HttpServletRequest req) { // REGISTO DE CONTA
														// NORMAL
		arUserClient.add(new UserClient(
				UserClientSQL.insertUserClient(req.getParameter("ip_name_na"), req.getParameter("ip_email_na"),
						req.getParameter("ip_repassword_na"), req.getParameter("ip_nick_na"),
						req.getParameter("ip_contact_na"), req.getParameter("ip_birth_na"), "", "",
						UserSQL.userTypeClient(), UserSQL.userStatusEnabled()),

				userType.searchUserType(UserSQL.userTypeClient()), req.getParameter("ip_name_na"),
				req.getParameter("ip_email_na"), req.getParameter("ip_contact_na"), req.getParameter("ip_nick_na"),
				req.getParameter("ip_birth_na"), ""));
		return "/index.jsp";
	}

	public String registUserProfessional(HttpServletRequest req) {// REGISTO DE
																	// CONTA
																	// PROFESSIONAL

		Logic.arUserProfessional.add(new UserProfessional(
				UserProfessionalSQL.insertUserProfessional(req.getParameter("ip_name_pc"),
						req.getParameter("ip_email_pc"), req.getParameter("ip_repassword_pc"),
						req.getParameter("ip_contact_pc"), req.getParameter("ip_birth_pc"), "",
						UserSQL.userTypeProfessional(), UserSQL.userStatusEnabled()),
				userType.searchUserType(UserSQL.userTypeProfessional()), req.getParameter("ip_name_pc"),
				req.getParameter("ip_email_pc"), req.getParameter("ip_contact_pc")));

		return "/index.jsp";
	}

	public boolean login(HttpServletRequest req) {

		int user_id = UserSQL.login(req.getParameter("ip_email_login"), req.getParameter("ip_password_login"));
		int user_user_type = UserSQL.userType(user_id);

		if (user_id != 0) {
			PageOnLoad.loadArrayLists();

			HttpSession sessao = req.getSession(true);

			sessao.setAttribute("user_user_type_id", user_user_type);
			sessao.setAttribute("user_id", user_id); // Guarda o ID do
														// utilizador numa
														// varavel de sessao
			return true;

		} else {

			req.setAttribute("erroLogin", "10");

			return false;
		}
	}

	public static UserClient searchUserClient(int user_id) { // Retornar um
																// objeto
																// UserClient

		for (UserClient uc : Logic.arUserClient) {
			if (uc.getUser_id() == user_id)
				return uc;
		}
		return null;
	}

	public void loadClientInformation(HttpServletRequest req) {
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");

		for (UserClient userClient : Logic.arUserClient) {
			if (userClient.getUser_id() == user_id) {
				req.setAttribute("user_id", userClient.getUser_id());
				req.setAttribute("user_name", userClient.getUser_name());
				req.setAttribute("user_nick", userClient.getUser_nick());
				req.setAttribute("user_email", userClient.getUser_email());
				req.setAttribute("user_contact", userClient.getUser_contact());
				req.setAttribute("user_birth", userClient.getUser_birth());
				req.setAttribute("user_pass", UserSQL.userPassword((int) sessao.getAttribute("user_id")));
			} else {

			}
		}
	}

	public void changeSettingsUser(HttpServletRequest req) {
		HttpSession sessao = req.getSession(true);

		UserClient userClient = searchUserClient((int) sessao.getAttribute("user_id"));

		if (UserSQL.userPassword(userClient.getUser_id())
				.equals(decryptPwd(req.getParameter("settings_old_password")))) { // Se
																					// a
																					// password
																					// antiga
																					// estiver
																					// correta

			String new_name = req.getParameter("settings_name_new");
			String new_email = req.getParameter("settings_name_new");
			String password = decryptPwd(req.getParameter("settings_new_password_confirm"));
			String new_nick = req.getParameter("settings_name_new");
			String new_contact = req.getParameter("settings_name_new");
			String new_birth = req.getParameter("settings_name_new");

			UserClientSQL.editUserClient(userClient.getUser_id(), new_name, new_email, password, new_nick, new_contact,
					new_birth, "", "");

			userClient.setUser_name(new_name);
			userClient.setUser_email(new_email);
			userClient.setUser_nick(new_nick);
			userClient.setUser_contact(new_contact);
			userClient.setUser_birth(new_birth);

		} else { // Se a password antiga estiver incorreta
			System.out.println("Password incorreta!");

			req.setAttribute("password_error_client", "1");

			loadClientInformation(req);
		}
	}

	public static String EncryptPwd(String password) { // Encriptar password

		char[] pwdc = password.toCharArray();
		int ca;
		char c;

		for (int i = 0; i < pwdc.length; i++) {
			c = pwdc[i];
			ca = ((int) c) - 5;
			c = (char) ca;

			pwdc[i] = c;

		}

		return String.copyValueOf(pwdc);
	}

	public static String decryptPwd(String password) { // Desencriptar password
		char[] pwdc = password.toCharArray();
		int ca;
		char c;

		for (int i = 0; i < pwdc.length; i++) {
			c = pwdc[i];
			ca = ((int) c) + 5;
			c = (char) ca;

			pwdc[i] = c;

		}

		return String.copyValueOf(pwdc);
	}

	// Adiciona Serviço ao ArrayList de Utilizador Professional
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

	public void logout(HttpServletRequest req) {
		HttpSession sessao = req.getSession(true);
		sessao.invalidate();

		Logic.arCategory.clear();
		Logic.arParty.clear();
		Logic.arPost.clear();
		Logic.arPrivilege.clear();
		Logic.arService.clear();
		Logic.arStatus.clear();
		Logic.arTable.clear();
		Logic.arUserClient.clear();
		Logic.arUserProfessional.clear();
		Logic.arUserType.clear();
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

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getParameter("action").equals("registNormal")) {
			registUser(req);
		} else if (req.getParameter("action").equals("registProfessional")) {
			registUserProfessional(req);
		} else if (req.getParameter("action").equals("login")) {
			login(req);
		} else if (req.getParameter("action").equals("logout")) {
			logout(req);
		} else if (req.getParameter("action").equals("changeSettings")) {
			changeSettingsUser(req);
		} else if (req.getParameter("action").equals("searchUser")) {
			loadClientInformation(req);
		} else if (req.getParameter("action").equals("sendServices")) {
			sendServices(req);
		} else if (req.getParameter("action").equals("deleteService")) {
			deleteService(req);
		} else if (req.getParameter("action").equals("editServiceUser")) {
			editServiceUser(req);
		}
		return "/index.jsp";
	}
}

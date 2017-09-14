package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserClient;
import model.UserProfessional;
import sql.UserClientSQL;
import sql.UserProfessionalSQL;
import sql.UserSQL;

public class SUser implements Logic {

	UserClientSQL userClientSQL = new UserClientSQL();
	UserSQL userSQL = new UserSQL();

	private static SUserType userType = new SUserType();

	public static String registUserClient(HttpServletRequest req) { // Registo de conta cliente

		arUserClient.add(new UserClient(
				UserClientSQL.insertUserClient(req.getParameter("ip_name_na"), req.getParameter("ip_email_na"),
												req.getParameter("ip_repassword_na"), req.getParameter("ip_nick_na"),
												req.getParameter("ip_contact_na"), req.getParameter("ip_birth_na"), "", "",
												UserSQL.userTypeClient(), UserSQL.userStatusEnabled()),
				
												userType.searchUserType(UserSQL.userTypeClient()), req.getParameter("ip_name_na"),
												req.getParameter("ip_email_na"), req.getParameter("ip_contact_na"),
												req.getParameter("ip_nick_na"), req.getParameter("ip_birth_na"), ""));

		return "/index.jsp";
	}

	public static String registUserProfessional(HttpServletRequest req) { // Registo de conta profissional

		arUserProfessional.add(new UserProfessional(
				UserProfessionalSQL.insertUserProfessional(req.getParameter("ip_name_pc"),
				req.getParameter("ip_email_pc"), req.getParameter("ip_repassword_pc"),
				req.getParameter("ip_contact_pc"),req.getParameter("ip_birth_pc") ,"", UserSQL.userTypeProfessional(),
				UserSQL.userStatusEnabled()),
				userType.searchUserType(UserSQL.userTypeProfessional()), req.getParameter("ip_name_pc"),
				req.getParameter("ip_email_pc"), req.getParameter("ip_contact_pc")));

		return "/index.jsp";
	}

	public static boolean login(HttpServletRequest req) { // Login

		int user_id = UserSQL.login(req.getParameter("ip_email_login"), req.getParameter("ip_password_login"));
		int user_user_type = UserSQL.userType(user_id);
		
		if (user_id != 0) {

			HttpSession sessao = req.getSession(true);

			sessao.setAttribute("user_id", user_id);
			sessao.setAttribute("user_user_type_id", user_user_type);
			
			PageOnLoad.loadArrayLists();
			
			return true;

		} else {
			req.setAttribute("erroLogin", "10");

			return false;
		}

	}

	public static void logout(HttpServletRequest req) {
		HttpSession sessao = req.getSession(true);
		sessao.invalidate();
		
		Logic.arCategory.clear();
		Logic.arUserClient.clear();
		Logic.arUserProfessional.clear();
		Logic.arParty.clear();
		Logic.arParty.clear();
		Logic.arPrivilege.clear();
		Logic.arService.clear();
		Logic.arStatus.clear();
		Logic.arTable.clear();
		Logic.arUserType.clear();
	}
	
	public static UserClient searchUserClient(int user_id) { // Retornar um objeto UserClient

		for (UserClient uc : Logic.arUserClient) {
			
			if (uc.getUser_id() == user_id) {
				return uc;
			}
		}
		
		return null;
	}

	public static void loadClientInformation(HttpServletRequest req) { // Carregar informaçoes do utilizador
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

	public static void changeSettingsUser(HttpServletRequest req) { // Mudar dados da conta

		HttpSession sessao = req.getSession(true);

		UserClient userClient = searchUserClient((int) sessao.getAttribute("user_id"));

		if (UserSQL.userPassword(userClient.getUser_id()).equals(decryptPwd(req.getParameter("settings_old_password")))) { // Se a password antiga estiver correta

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
			;
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

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getParameter("action").equals("registNormal")) {
			registUserClient(req);
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
		}

		return "/index.jsp";
	}

}

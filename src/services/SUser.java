package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserClient;
import sql.UserClientSQL;

public class SUser implements Logic {

	UserClientSQL userClientSQL = new UserClientSQL();

	public boolean login(HttpServletRequest req) {

		int user_id = userClientSQL.login(req.getParameter("user_email"), req.getParameter("user_password"));

		if (user_id != 0) {
			// PageOnLoad.carregarInformacao();

			HttpSession sessao = req.getSession(true);

			sessao.setAttribute("user_id", user_id); // Guarda o ID do utilizador numa varavel de sessao
			// sessao.setAttribute("user_tipo", procurarUser(user_id).getUser_tipo());
			// //Guarda o ID do tipo de utilizador numa varavel de sessao

			return true; // Se o login for efetuado com sucesso retorna true
		}

		return false; // Se o login nao for efetuado com sucesso retorna false
	}

	public UserClient searchUserClient(int user_id) {

		for (UserClient uc : Logic.arUserClient) {
			if (uc.getUser_id() == user_id)
				return uc;
		}

		return null;
	}

	public static String EncryptPwd(String password) {

		char[] pwdc = password.toCharArray();
		int ca;
		char c;

		for (int i = 0; i < pwdc.length; i++) {
			c = pwdc[i];
			ca = ((int) c) - 29;
			c = (char) ca;

			pwdc[i] = c;

		}

		return String.copyValueOf(pwdc);
	}

	public static String decryptPwd(String password) {
		char[] pwdc = password.toCharArray();
		int ca;
		char c;

		for (int i = 0; i < pwdc.length; i++) {
			c = pwdc[i];
			ca = ((int) c) + 29;
			c = (char) ca;

			pwdc[i] = c;

		}
		
		return String.copyValueOf(pwdc);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getParameter("acao").equals("login")) {
			System.out.println(login(req));
		}

		return "/index.jsp";
	}

}

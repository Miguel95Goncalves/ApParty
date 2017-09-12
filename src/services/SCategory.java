package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;

public class SCategory implements Logic {

	public static Category searchCategory(int category_id) {

		for (int i = 0; i < Logic.arCategory.size(); i++) {
			if (Logic.arCategory.get(i).getCategory_id() == category_id)
				return Logic.arCategory.get(i);
		}

		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getParameter("acao").equals("searchCategory")) {
		}

		return "/index.jsp";
	}

}

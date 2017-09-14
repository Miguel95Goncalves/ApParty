package services;

import java.util.ArrayList;

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
	
	public static int categoryId( int category_id){
		for(Category c:Logic.arCategory){
			if(c.getCategory_id()==category_id)
				return c.getCategory_id();
		}
		return 0;
	}
	
	public static ArrayList<Category> loadCategoryInformation(HttpServletRequest req){
		ArrayList<Category> category = new ArrayList<Category>();
		category.addAll(Logic.arCategory);
		return category;
	}
	
	public static int sendCategoryId(int category_id){
		for(Category  cat: Logic.arCategory){
			if(cat.getCategory_id() == category_id){
				return cat.getCategory_id();
			}
		}
		return 0;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getParameter("acao").equals("searchCategory")) {
		}

		return "/index.jsp";
	}

}

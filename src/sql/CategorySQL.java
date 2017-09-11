package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Category;
import services.Logic;
import services.SStatus;

public class CategorySQL {
	public static void loadCategory() { // Carregar categorias
		String categorys = "SELECT category_id, category_name, category_status_id FROM Category";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(categorys);

			SStatus sStatus = new SStatus();

			while (rs.next()) {
				Logic.arCategory.add(new Category(rs.getInt("category_id"), rs.getString("category_name"),
						sStatus.searchStatus(rs.getInt("category_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadCategorys");
			System.err.println(e.getMessage());
		}
	}
}

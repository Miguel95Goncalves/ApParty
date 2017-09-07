package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Table;

public class STable implements Logic {

	public Table searchTable(int table_id) {
		
		for(Table table : Logic.arTable) {
			if(table.getTable_id() == table_id) return table;
		}
		
		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

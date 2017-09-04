package model;

public class Table {

	private int table_id;
	private String table_name;
	
	public Table() {
		super();
	}
	
	public Table(int table_id, String table_name) {
		super();
		this.table_id = table_id;
		this.table_name = table_name;
	}
	
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
}

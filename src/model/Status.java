package model;

public class Status {

	private int status_id;
	private String status_name;
	private Table status_table;
	
	public Status() {
		super();
	}

	public Status(int status_id, String status_name, Table status_table) {
		super();
		this.status_id = status_id;
		this.status_name = status_name;
		this.status_table = status_table;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public Table getStatus_table() {
		return status_table;
	}

	public void setStatus_table(Table status_table) {
		this.status_table = status_table;
	}

}

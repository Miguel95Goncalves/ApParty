package model;

public class Category {

	private int category_id;
	private String category_name;
	private Status status;
	
	public Category(int category_id, String category_name, Status status) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.status = status;
	}
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

}

package model;

public class Privilege {
	
	private int privilege_id, privilege_days;
	private float privilege_price;
	private Status privilege_status;
	
	public Privilege() {
		super();
	}

	public Privilege(int privilege_id, int privilege_days, float privilege_price, Status privilege_status) {
		super();
		this.privilege_id = privilege_id;
		this.privilege_days = privilege_days;
		this.privilege_price = privilege_price;
		this.privilege_status = privilege_status;
	}

	public int getPrivilege_id() {
		return privilege_id;
	}

	public void setPrivilege_id(int privilege_id) {
		this.privilege_id = privilege_id;
	}

	public int getPrivilege_days() {
		return privilege_days;
	}

	public void setPrivilege_days(int privilege_days) {
		this.privilege_days = privilege_days;
	}

	public float getPrivilege_price() {
		return privilege_price;
	}

	public void setPrivilege_price(float privilege_price) {
		this.privilege_price = privilege_price;
	}

	public Status getPrivilege_status() {
		return privilege_status;
	}

	public void setPrivilege_status(Status privilege_status) {
		this.privilege_status = privilege_status;
	}

}

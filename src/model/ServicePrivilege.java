package model;

public class ServicePrivilege {
	private int sp_id;
	private Privilege sp_privilege;
	private String sp_initial_date, sp_final_date;
	private Status sp_status;
	
	public ServicePrivilege(int sp_id, Privilege sp_privilege, String sp_initial_date, String sp_final_date,
			Status sp_status) {
		super();
		this.sp_id = sp_id;
		this.sp_privilege = sp_privilege;
		this.sp_initial_date = sp_initial_date;
		this.sp_final_date = sp_final_date;
		this.sp_status = sp_status;
	}
	
	public int getSp_id() {
		return sp_id;
	}
	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}
	public Privilege getSp_privilege() {
		return sp_privilege;
	}
	public void setSp_privilege(Privilege sp_privilege) {
		this.sp_privilege = sp_privilege;
	}
	public String getSp_initial_date() {
		return sp_initial_date;
	}
	public void setSp_initial_date(String sp_initial_date) {
		this.sp_initial_date = sp_initial_date;
	}
	public String getSp_final_date() {
		return sp_final_date;
	}
	public void setSp_final_date(String sp_final_date) {
		this.sp_final_date = sp_final_date;
	}
	public Status getSp_status() {
		return sp_status;
	}
	public void setSp_status(Status sp_status) {
		this.sp_status = sp_status;
	}
}

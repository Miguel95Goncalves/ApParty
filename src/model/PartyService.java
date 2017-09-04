package model;

public class PartyService {
	private int ps_id;
	private int ps_service_id;
	
	public PartyService(int ps_id, int ps_service_id) {
		super();
		this.ps_id = ps_id;
		this.ps_service_id = ps_service_id;
	}
	
	public int getPs_id() {
		return ps_id;
	}
	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}
	public int getPs_service_id() {
		return ps_service_id;
	}
	public void setPs_service_id(int ps_service_id) {
		this.ps_service_id = ps_service_id;
	}
	
}

package model;

public class Service {
	
	private int service_id;
	private Privilege service_privilege;
	private String service_name, service_description, service_images, service_final_date;
	private Status service_status;
	
	public Service() {
		super();
	}

	public Service(int service_id, Privilege service_privilege, String service_name, String service_description,
			String service_images, String service_final_date, Status service_status) {
		super();
		this.service_id = service_id;
		this.service_privilege = service_privilege;
		this.service_name = service_name;
		this.service_description = service_description;
		this.service_images = service_images;
		this.service_final_date = service_final_date;
		this.service_status = service_status;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public Privilege getService_privilege() {
		return service_privilege;
	}

	public void setService_privilege(Privilege service_privilege) {
		this.service_privilege = service_privilege;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_description() {
		return service_description;
	}

	public void setService_description(String service_description) {
		this.service_description = service_description;
	}

	public String getService_images() {
		return service_images;
	}

	public void setService_images(String service_images) {
		this.service_images = service_images;
	}

	public String getService_final_date() {
		return service_final_date;
	}

	public void setService_final_date(String service_final_date) {
		this.service_final_date = service_final_date;
	}

	public Status getService_status() {
		return service_status;
	}

	public void setService_status(Status service_status) {
		this.service_status = service_status;
	}
	
}
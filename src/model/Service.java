package model;

public class Service {
	
	private int service_id;
	private String service_name, service_tiny_description, service_full_description, service_images;
	private Status service_status;
	
	public Service() {
		super();
	}
	
	public Service(int service_id, String service_name, String service_tiny_description,
			String service_full_description, String service_images, Status service_status) {
		super();
		this.service_id = service_id;
		this.service_name = service_name;
		this.service_tiny_description = service_tiny_description;
		this.service_full_description = service_full_description;
		this.service_images = service_images;
		this.service_status = service_status;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_tiny_description() {
		return service_tiny_description;
	}

	public void setService_tiny_description(String service_tiny_description) {
		this.service_tiny_description = service_tiny_description;
	}

	public String getService_full_description() {
		return service_full_description;
	}

	public void setService_full_description(String service_full_description) {
		this.service_full_description = service_full_description;
	}

	public String getService_images() {
		return service_images;
	}

	public void setService_images(String service_images) {
		this.service_images = service_images;
	}

	public Status getService_status() {
		return service_status;
	}

	public void setService_status(Status service_status) {
		this.service_status = service_status;
	}
	
}
package model;

public class Post {

	private int post_id;
	private User post_user;
	private Party post_party;
	
	public Post() {
		super();
	}
	
	public Post(int post_id, User post_user, Party post_party) {
		super();
		this.post_id = post_id;
		this.post_user = post_user;
		this.post_party = post_party;
	}
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public User getPost_user() {
		return post_user;
	}
	public void setPost_user(User post_user) {
		this.post_user = post_user;
	}
	public Party getPost_party() {
		return post_party;
	}
	public void setPost_party(Party post_party) {
		this.post_party = post_party;
	}

}

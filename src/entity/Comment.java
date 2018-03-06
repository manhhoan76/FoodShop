package entity;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private int id_user;
	private String username;
	private String image;
	private int id_product;
	private String content;
	private Timestamp create_at;
	private int slide;
	public Comment(int id, int id_user, String username, String image, int id_product, String content,
			Timestamp create_at, int slide) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.username = username;
		this.image = image;
		this.id_product = id_product;
		this.content = content;
		this.create_at = create_at;
		this.slide = slide;
	}
	public Comment() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	public int getSlide() {
		return slide;
	}
	public void setSlide(int slide) {
		this.slide = slide;
	}
	
	
}

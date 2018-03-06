package entity;

import java.sql.Timestamp;

public class Suggest {
	private int id;
	private int id_user;
	private String name;
	private int price;
	private String image;
	private String content;
	private Timestamp create_at;
	public Suggest(int id, int id_user, String name, int price, String image, String content, Timestamp create_at) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.name = name;
		this.price = price;
		this.image = image;
		this.content = content;
		this.create_at = create_at;
	}
	public Suggest() {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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

}

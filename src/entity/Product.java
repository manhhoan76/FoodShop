package entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Product {
	private int id;
	@NotEmpty(message="Không được để trống")
	private String name;
	private int id_cat;
	@NotEmpty(message="Không được để trống")
	private String description;
	private int price;
	private String image;
	private Timestamp create_at;
	private int slide;
	public Product(int id, String name, int id_cat, String description, int price, String image,
			Timestamp create_at, int slide) {
		super();
		this.id = id;
		this.name = name;
		this.id_cat = id_cat;
		this.description = description;
		this.price = price;
		this.image = image;
		this.create_at = create_at;
		this.slide = slide;
	}
	public Product() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

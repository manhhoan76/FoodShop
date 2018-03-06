package entity;

import java.sql.Timestamp;

public class OrderDetail {
	private int id;
	private int id_product;
	private int id_order;
	private int quantity;
	private int price;
	private Timestamp create_at;
	private String name;
	private String image;
	public OrderDetail(int id, int id_product, int id_order, int quantity, int price, Timestamp create_at, String name,
			String image) {
		super();
		this.id = id;
		this.id_product = id_product;
		this.id_order = id_order;
		this.quantity = quantity;
		this.price = price;
		this.create_at = create_at;
		this.name = name;
		this.image = image;
	}
	public OrderDetail() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}

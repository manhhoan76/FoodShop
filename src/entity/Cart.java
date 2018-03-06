package entity;

public class Cart {
	private int id_product;
	private String name;
	private String image;
	private int quantity;
	private int price;
	public Cart(int id_product, String name, String image, int quantity, int price) {
		super();
		this.id_product = id_product;
		this.name = name;
		this.image = image;
		this.quantity = quantity;
		this.price = price;
	}
	public Cart() {
		super();
	}
	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
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
	
}

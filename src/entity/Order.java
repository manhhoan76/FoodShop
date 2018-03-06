package entity;

import java.sql.Timestamp;

public class Order {
	private int id;
	private int id_user;
	private int id_order_status;
	private String status;
	private String address;
	private String username;
	private String phone;
	private Timestamp create_at;
	private int sumPrice;
	private String pay;
	public Order(int id, int id_user, int id_order_status, String status, String address, String username, String phone,
			Timestamp create_at, int sumPrice, String pay) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.id_order_status = id_order_status;
		this.status = status;
		this.address = address;
		this.username = username;
		this.phone = phone;
		this.create_at = create_at;
		this.sumPrice = sumPrice;
		this.pay = pay;
	}
	public Order() {
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
	public int getId_order_status() {
		return id_order_status;
	}
	public void setId_order_status(int id_order_status) {
		this.id_order_status = id_order_status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	public int getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	
}

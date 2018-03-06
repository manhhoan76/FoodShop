package entity;

import org.hibernate.validator.constraints.NotEmpty;

public class Role {
	private int role_id;
	@NotEmpty(message="Không được để trống")
	private String name;
	public Role(int role_id, String name) {
		super();
		this.role_id = role_id;
		this.name = name;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role() {
		super();
	}
	

}

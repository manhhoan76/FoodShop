package entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Cat {
	private int id;
	private String name;
	private String description;
	private Timestamp create_at;
	public Cat(int id, String name, String description, Timestamp create_at) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.create_at = create_at;
	}
	public Cat() {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	
	
}

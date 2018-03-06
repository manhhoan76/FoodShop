package entity;

import java.sql.Timestamp;

public class Discount {
	private int id;
	private String name;
	private int used;
	private int percent;
	private Timestamp create_at;
	public Discount(int id, String name, int used, int percent, Timestamp create_at) {
		super();
		this.id = id;
		this.name = name;
		this.used = used;
		this.percent = percent;
		this.create_at = create_at;
	}
	public Discount() {
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
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
}

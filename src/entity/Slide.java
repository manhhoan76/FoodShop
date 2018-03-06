package entity;

import java.sql.Timestamp;

public class Slide {
	private int id;
	private String name;
	private int active;
	private Timestamp create_at;
	private String link;
	public Slide(int id, String name, int active, Timestamp create_at, String link) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.create_at = create_at;
		this.link = link;
	}
	public Slide() {
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}

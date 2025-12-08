package main.entities;

import java.util.UUID;

public class User {

	private UUID id;
	private String user;

	public User() {
		this.id = UUID.randomUUID();
	}

	public User(String user) {
		this();
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + user + "]";
	}

}

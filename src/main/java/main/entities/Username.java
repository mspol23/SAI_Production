package main.entities;

import java.util.UUID;

public class Username {

	private final UUID id;
	private String username;
	private String user_id;

	public Username() {
		this.id = UUID.randomUUID();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Username [id=" + id + ", username=" + username + ", user_id=" + user_id + "]";
	}

}

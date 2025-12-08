package main.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Production {

	private static final DateTimeFormatter DTMF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private UUID id;
	private String type;
	private String username;
	private String usernameId;
	private String num;
	private LocalDateTime dateTime;
	private String subject;
	private String assigned;
	private String status;
	private String origin;

	public Production() {
		this.id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameId() {
		return usernameId;
	}

	public void setUsernameId(String usernameId) {
		this.usernameId = usernameId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getDateTimeAsString() {
		return DTMF.format(dateTime);
	}

	public void setDateTime(String dateTime) {
		this.dateTime = LocalDateTime.parse(dateTime, DTMF);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}

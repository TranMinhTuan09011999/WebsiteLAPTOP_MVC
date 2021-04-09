package minhtuan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Admin")
public class Admin {
	
	@Id
	@Column(name = "Username", unique = true, nullable = false, length = 50)
	private String username;
	
	@Column(name = "Password", nullable = false, length = 50)
	private String password;
	
	@Column(name = "FullName", nullable = false)
	private String fullName;
	
	@Column(name = "Access", nullable = false)
	private Integer access;

	public Admin() {
	}

	public Admin(String username, String password, String fullName, Integer access) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.access = access;
	}

	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getAccess() {
		return this.access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

}

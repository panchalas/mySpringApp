package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="Register")
public class Register 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	@NotEmpty(message="User Name should not be empty")
	private String UserName;

	@Length(min=8, max=20)
	@NotEmpty(message="Password should not be empty")
	private String Password;

	@Length(min=2, max=20)
	@NotEmpty(message="First Name should not be empty")
	private String FirstName;

	@Length(min=2, max=20)
	@NotEmpty(message="Last Name should not be empty")
	private String LastName;
	
	@NotEmpty(message="Email should not be empty")
	private String Email;

	private int Active;
	private int USER_ROLE_ID;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String username) {
		this.UserName = username;
	}
	
	public String getPassword() {
		return Password;
	}

	public void setPassword(String pwd) {
		this.Password = pwd;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getActive() {
		return Active;
	}

	public void setActive(int active) {
		Active = active;
	}

	public int getUSER_ROLE_ID() {
		return USER_ROLE_ID;
	}

	public void setUSER_ROLE_ID(int uSER_ROLE_ID) {
		USER_ROLE_ID = uSER_ROLE_ID;
	}
	
}

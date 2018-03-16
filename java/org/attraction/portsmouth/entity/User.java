package org.attraction.portsmouth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {

	@Id
	@Column(name = "iduser")
	@GeneratedValue
	private Integer iduser;
	@NotEmpty(message="Username field needs to be filled")
	@Column(name = "username", unique = true)
	private String username;

	@NotEmpty(message="Password field needs to be filled")
	@Column(name = "password")
	private String password;


	@NotEmpty(message="First Name field needs to be filled")
	@Column(name = "firstname")
	private String firstname;

	@NotEmpty (message="Last Name field needs to be filled")
	@Column(name = "lastname")
	private String lastname;

	
	@NotEmpty(message="Phone Number field needs to be filled")
	@Column(name = "phoneno")
	private String phoneno;

	@NotEmpty(message="Email field needs to be filled")
	@Email(message="This is not a valid Email Address")
	@Column(name = "email", unique = true)
	private String email;
	
	@NotEmpty(message="Address field needs to be filled")
	@Column(name = "address")
	private String address;
	
	@NotEmpty
	@Column(name = "active")
	private String active;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

}

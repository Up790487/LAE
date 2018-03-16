package org.attraction.portsmouth.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "attraction", uniqueConstraints = {@UniqueConstraint(columnNames = "name") })
public class Attraction {

	@Id
	@Column(name = "idattraction")
	@GeneratedValue
	private Integer idattraction;

	@NotEmpty(message="The attraction name cannot be empty")
	@Column(name = "name", unique = true)
	private String name;

	@NotEmpty (message="The attraction description cannot be empty")
	@Column(name = "description")
	private String description;
	
	
	@NotEmpty(message="The attraction address cannot be empty")
	@Column(name = "address")
	private String address;
	
	@NotEmpty(message="The attraction category cannot be empty")
	@Column(name = "category")
	private String category;
	
	@DateTimeFormat(pattern="HH:mm")
	@Column(name = "starthour")
	private Date starthour;
	
	@DateTimeFormat(pattern="HH:mm")
	@Column(name = "endhour")
	private Date endhour;
	
	@Column(name = "picture")
	private String picture;	
	
	@NotEmpty
	@Column(name = "username")
	private String username;
	
	@NotEmpty(message="Attraction Status field needs to be filled")
	@Column(name = "status")
	private String status;

	public Integer getIdattraction() {
		return idattraction;
	}

	public void setIdattraction(Integer idattraction) {
		this.idattraction = idattraction;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getStarthour() {
		return starthour;
	}

	public void setStarthour(Date starthour) {
		this.starthour = starthour;
	}

	public Date getEndhour() {
		return endhour;
	}

	public void setEndhour(Date endhour) {
		this.endhour = endhour;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		

}

package io.jg_intelligence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="First Name is Required")
	private String firstName;
	@NotBlank(message = "Last Name is Required")
	private String lastName;
	private String middleName;
	@NotBlank(message = "Email is Required")
	@Email(message = "Email needs to be a valid Email")
	@Column(unique = true)
	private String email;
	@NotBlank(message = "Id number is Required")
	@Column(unique = true)
	private String idNumber;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date createAt;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date updateAt;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Admin admin;
	
	
	@PrePersist
	protected void onCreate() {
		this.createAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updateAt = new Date();
	}
	
	
	public Teacher() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	
	
	
}

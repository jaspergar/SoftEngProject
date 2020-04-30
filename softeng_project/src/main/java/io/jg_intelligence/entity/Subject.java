package io.jg_intelligence.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Subject Prefix name is Required")
	@Column(updatable = false,unique = true)
	private String subjectNamePrefix;
	@NotBlank(message = "Subject Fullname is Required")
	private String subjectFullname;
	@NotBlank(message = "Subject Type standing is Required")
	private String subjectType;
	@NotBlank(message = "College standing is Required")
	private String collegeStanding;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date createAt;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date updateAt;
//	
	@OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL, mappedBy = "subject")
	@JsonIgnore
	private Backlog backlog;
	
	@PrePersist
	protected void onCreate() {
		this.createAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updateAt = new Date();
	}
	
	public Subject() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSubjectNamePrefix() {
		return subjectNamePrefix;
	}


	public void setSubjectNamePrefix(String subjectNamePrefix) {
		this.subjectNamePrefix = subjectNamePrefix;
	}


	public String getSubjectFullname() {
		return subjectFullname;
	}


	public void setSubjectFullname(String subjectFullname) {
		this.subjectFullname = subjectFullname;
	}


	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	
	public String getCollegeStanding() {
		return collegeStanding;
	}
	public void setCollegeStanding(String collegeStanding) {
		this.collegeStanding = collegeStanding;
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
	public Backlog getBacklog() {
		return backlog;
	}
	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
	
	
	
	
	
}

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer isTaken=0;
	@NotBlank(message = "Day is Required")
	private String day;
	@NotBlank(message = "Time-in is Required")
	private String timeIn;
	@NotBlank(message = "Time-out is Required")
	private String timeOut;
	@NotBlank(message = "Please insert Room number")
	private String roomNumber;
	private String subjectPrefix;
	@Column(unique = true)
	private String edpCode;
	private String teacherId;
	
	private Date createAt;
	private Date updateAt;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "backlog_id",updatable = false,nullable = false)
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
	
	public Schedule() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}





	public Integer getIsTaken() {
		return isTaken;
	}
	public void setIsTaken(Integer isTaken) {
		this.isTaken = isTaken;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	

	public String getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}
	public String getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getSubjectPrefix() {
		return subjectPrefix;
	}
	public void setSubjectPrefix(String subjectPrefix) {
		this.subjectPrefix = subjectPrefix;
	}
	public String getEdpCode() {
		return edpCode;
	}
	public void setEdpCode(String edpCode) {
		this.edpCode = edpCode;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
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

package io.jg_intelligence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubjectAndScheduleJoin {

	@Id
	@GeneratedValue()
	private Long id;
	private String subjectNamePrefix;
	private String subjectFullname;
	private String subjectType;
	private String collegeStanding;
	private Long schedId;
	private String roomNumber;
	public SubjectAndScheduleJoin(Long id, String subjectNamePrefix, String subjectFullname, String subjectType,
			String collegeStanding, Long schedId, String roomNumber) {
		this.id = id;
		this.subjectNamePrefix = subjectNamePrefix;
		this.subjectFullname = subjectFullname;
		this.subjectType = subjectType;
		this.collegeStanding = collegeStanding;
		this.schedId = schedId;
		this.roomNumber = roomNumber;
	}
	public SubjectAndScheduleJoin() {
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
	public Long getSchedId() {
		return schedId;
	}
	public void setSchedId(Long schedId) {
		this.schedId = schedId;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	
}

 package io.jg_intelligence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer scheduleSequence=0;
	private String subjectNamePrefix;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id",nullable = false)
	@JsonIgnore
	private Subject subject;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH, mappedBy = "backlog",orphanRemoval = true)
	private List<Schedule> schedule = new ArrayList<>();

	

	public Backlog() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getScheduleSequence() {
		return scheduleSequence;
	}

	public void setScheduleSequence(Integer scheduleSequence) {
		this.scheduleSequence = scheduleSequence;
	}



	public String getSubjectNamePrefix() {
		return subjectNamePrefix;
	}

	public void setSubjectNamePrefix(String subjectNamePrefix) {
		this.subjectNamePrefix = subjectNamePrefix;
	}



	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}
	
	
	
	
}

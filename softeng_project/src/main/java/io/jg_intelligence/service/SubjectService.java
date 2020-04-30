package io.jg_intelligence.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jg_intelligence.entity.Backlog;
import io.jg_intelligence.entity.Schedule;
import io.jg_intelligence.entity.Subject;
import io.jg_intelligence.exception.SubjectAlreadyExistException;
import io.jg_intelligence.exception.SubjectNotFoundException;
import io.jg_intelligence.repository.BacklogRepository;
import io.jg_intelligence.repository.ScheduleRepository;
import io.jg_intelligence.repository.SubjectRepository;

@Service
public class SubjectService {
    
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public Subject saveOrUpdateSubject(Subject newSubject) {
		
		try {
			newSubject.setSubjectNamePrefix(newSubject.getSubjectNamePrefix().toUpperCase());
			Backlog backlog = new Backlog();
			//create subject
			if(newSubject.getId()==null) {
				
				newSubject.setBacklog(backlog);
				backlog.setSubject(newSubject);
				backlog.setSubjectNamePrefix(newSubject.getSubjectNamePrefix().toUpperCase());
			}
//			update subject
			if(newSubject.getId()!=null) {
			   newSubject.setBacklog(backlogRepository.findBySubjectNamePrefix(newSubject.getSubjectNamePrefix().toUpperCase()));
			}
			return subjectRepository.save(newSubject);
		} catch (Exception e) {
			// TODO: handle exception
			throw new SubjectAlreadyExistException("Subject "+newSubject.getSubjectNamePrefix()+" Prefix Already Taken");
		}
	} 
	
	public Subject getSubjectBySubNameSubjectPrefix(String subjectPrefix){
		Subject subject2 = subjectRepository.findOneBySubjectNamePrefix(subjectPrefix.toUpperCase());
		
		if(subject2 == null) {
			throw new SubjectNotFoundException("Subject "+subjectPrefix+" Not found");
		}
		
		return subject2;
	}
	
	public List<Subject> findAllSubject(){
		List<Subject> subject = subjectRepository.findAllByOrderByIdDesc();
		return subject;		
	}
	
	public void deleteSubject(String subjectPrefix) {
		subjectRepository.delete(getSubjectBySubNameSubjectPrefix(subjectPrefix));
	}
	
	public List<Subject> getAllSubjectAndSchedule(){
		List<Subject> subjectsAndSchedule = subjectRepository.findSubjectAndSchedule();
		return subjectsAndSchedule;
	}
	
}

package io.jg_intelligence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jg_intelligence.entity.Admin;
import io.jg_intelligence.entity.Teacher;
import io.jg_intelligence.exception.TeacherIdNumAlreadyExistException;
import io.jg_intelligence.exception.TeacherNotFoundException;
import io.jg_intelligence.repository.AdminRepository;
import io.jg_intelligence.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired 
	private TeacherRepository teacherRepository;
	@Autowired
	private AdminRepository adminRepository;
	
	//create and update teacher
	public Teacher addUpdateTeacher(Teacher newTeacher,String username) {
	  if(newTeacher.getId() != null) {
		  Teacher existingTeacher = teacherRepository.findOneByIdNumber(newTeacher.getIdNumber());
		  
		  if(existingTeacher == null) {
			  throw new TeacherNotFoundException("Teacher with id number of"+newTeacher.getIdNumber()+" is not found");
		  }
	  }	
	  
	  try {
		Admin admin = adminRepository.findByUsername(username);
		newTeacher.setAdmin(admin);
		newTeacher.setIdNumber(newTeacher.getIdNumber().toUpperCase());
		return teacherRepository.saveAndFlush(newTeacher);
	} catch (Exception e) {
		// TODO: handle exception
		throw new TeacherIdNumAlreadyExistException("Teacher with the id number of "+newTeacher.getIdNumber()+" is already taken");
	}
		
	}
	
	
	//retrieve one teacher
	public Teacher findOneTeacher(String idNumber) {
		Teacher teacher = teacherRepository.findOneByIdNumber(idNumber);
		if(teacher == null) {
			throw new TeacherNotFoundException("Teacher with id number of "+ idNumber +" does not exist");
		}
		return teacher;
	}
	//retrieve all teacher
	public List<Teacher> findAllTeacher(){
		List<Teacher> teacherList = teacherRepository.findAll();
		return teacherList;
	}
	//delete one teacher
	public void deleteTeacherByIdNumber(String idNumber) {
		teacherRepository.delete(findOneTeacher(idNumber));
	}
	//delete all teacher
	//delete all selected teacher
}

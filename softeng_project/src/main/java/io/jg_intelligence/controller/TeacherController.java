package io.jg_intelligence.controller;

import java.security.Principal;
import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jg_intelligence.entity.Teacher;
import io.jg_intelligence.service.MapValidatorService;
import io.jg_intelligence.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

	@Autowired
	private MapValidatorService mapValidatorService;
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping
	public ResponseEntity<?> addOrUpdateTeacher(@Valid @RequestBody Teacher teacher,BindingResult result, Principal principal){
		ResponseEntity<?> errorMap = mapValidatorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		Teacher  teacher2 = teacherService.addUpdateTeacher(teacher, principal.getName());
		return new ResponseEntity<Teacher> (teacher2,HttpStatus.CREATED);
	}
	
	@GetMapping("/{idNumber}")
	public ResponseEntity<?> findOneTeacherByIdNumber(@PathVariable String idNumber){
		Teacher teacher = teacherService.findOneTeacher(idNumber);
		return new ResponseEntity<Teacher> (teacher,HttpStatus.OK);
	}
	@GetMapping("/all")
	public List<Teacher> findAllTeacher(){
		return teacherService.findAllTeacher();
	}
	@DeleteMapping("/delete/{idNumber}")
	public ResponseEntity<?> deleteOneTeacherByIdNumber(@PathVariable String idNumber){
		teacherService.deleteTeacherByIdNumber(idNumber);
		return new ResponseEntity<String> ("Teacher With Id Number of " +idNumber+ " was successfully deleted",HttpStatus.OK);
	}
	
}

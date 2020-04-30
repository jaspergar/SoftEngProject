package io.jg_intelligence.controller;



import java.util.List;

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

import io.jg_intelligence.entity.Schedule;
import io.jg_intelligence.entity.Subject;
import io.jg_intelligence.service.MapValidatorService;
import io.jg_intelligence.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	@Autowired
	MapValidatorService mapValidatorService;
      @Autowired
      private SubjectService subjectService;
      
      @PostMapping
      public ResponseEntity<?> addSubject(@Valid @RequestBody Subject subject,BindingResult result){                 
    	   ResponseEntity<?> errorMap = mapValidatorService.MapValidationService(result);
    	   if(errorMap!=null) return errorMap;
    	   
    	   Subject subject2 = subjectService.saveOrUpdateSubject(subject);
    	   return new ResponseEntity<Subject> (subject2,HttpStatus.CREATED);
      }
     
      @GetMapping("/{subName_prefix}")
      public ResponseEntity<?> getSubjectBySubPrefix(@PathVariable String subName_prefix){
    	  Subject subject = subjectService.getSubjectBySubNameSubjectPrefix(subName_prefix.toUpperCase());
    	  return new ResponseEntity<Subject> (subject,HttpStatus.OK);
      }
      
      @GetMapping("/all")
      public List<Subject> getAllSubjectByNamePrefix(){
    	  return subjectService.findAllSubject();
      }
      
      @DeleteMapping("/{subName_prefix}")
      public ResponseEntity<?> deleteSubjectByNamePrefix(@PathVariable String subName_prefix){
    	  subjectService.deleteSubject(subName_prefix.toUpperCase());
    	  return new ResponseEntity<String>("Subject "+subName_prefix.toUpperCase()+" was successfully deleted.",HttpStatus.OK);
      }
      
      @GetMapping("/allSubAndSched")
      public List<Subject> getAllSubjectAndSchedule(){
    	  return subjectService.getAllSubjectAndSchedule();
      }
     
}

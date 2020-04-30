package io.jg_intelligence.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jg_intelligence.entity.Schedule;
import io.jg_intelligence.service.MapValidatorService;
import io.jg_intelligence.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin
public class ScheduleController {

	@Autowired 
	private ScheduleService scheduleService;
	
	@Autowired 
	private MapValidatorService mapValidatorService;
	
	@PostMapping("/{subject_prefix}")
	public ResponseEntity<?> createSchedule(@Valid @RequestBody Schedule schedule,BindingResult result,@PathVariable String subject_prefix){
		ResponseEntity<?> errorMap = mapValidatorService.MapValidationService(result);
		if(errorMap!=null) return errorMap; 
		Schedule schedule2 = scheduleService.addSchedule(subject_prefix, schedule);
		return new ResponseEntity<Schedule> (schedule2,HttpStatus.CREATED);
	}
	@GetMapping("/{day}/{roomNumber}")
	public List<Schedule> getAllScheduleBasedOnDayAndRoomNumber(@PathVariable String day,@PathVariable String roomNumber){
		return scheduleService.getAllScheduleBasedOnDayAndRoomNumber(day, roomNumber);
	}
	

	
  @GetMapping("/find/{subjectPrefix}/{edpCode}")
     public ResponseEntity<?> getScheduleByEdpCode(@PathVariable String subjectPrefix,@PathVariable String edpCode){
	  Schedule schedule = scheduleService.findScheduleByEdpCode(edpCode, subjectPrefix);
         return new ResponseEntity<Schedule>(schedule,HttpStatus.OK);
     }
	
	@PatchMapping("/{subjectPrefix}/{edpCode}")
	public ResponseEntity<?> updateScheduleOrAssignTeacher(@Valid @RequestBody Schedule schedule,BindingResult result,@PathVariable String subjectPrefix,@PathVariable String edpCode){
	    ResponseEntity<?> errorMap = mapValidatorService.MapValidationService(result);
	    if(errorMap!=null) return errorMap;
	    Schedule schedule2 = scheduleService.updateScheduleOrAssignTeacherToSchedule(schedule, edpCode, subjectPrefix);
	    return new ResponseEntity<Schedule> (schedule2,HttpStatus.OK);
	}
	@DeleteMapping("/{subjectPrefix}/{edpCode}")
	public ResponseEntity<?> deleteSchedule(@PathVariable String subjectPrefix,@PathVariable String edpCode){
		scheduleService.deleteSchedule(edpCode, subjectPrefix);
		return new ResponseEntity<String> ("Schedule "+edpCode+ " was deleted.",HttpStatus.OK);
	}
}	

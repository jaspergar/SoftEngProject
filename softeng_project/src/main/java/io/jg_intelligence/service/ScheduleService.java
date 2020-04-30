package io.jg_intelligence.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import io.jg_intelligence.entity.Backlog;
import io.jg_intelligence.entity.Schedule;
import io.jg_intelligence.exception.InvalidTimeException;
import io.jg_intelligence.exception.SubjectNotFoundException;
import io.jg_intelligence.repository.ScheduleRepository;
import io.jsonwebtoken.lang.Strings;

@Service
public class ScheduleService {

	@Autowired 
	private ScheduleRepository scheduleRepository;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private CheckTimeService checkTimeService;
	
	public Schedule addSchedule(String subject_prefix,Schedule newSchedule){
		     //checking if subject exist via backlog
			 Backlog backlog = subjectService.getSubjectBySubNameSubjectPrefix(subject_prefix).getBacklog();
		     //setting the backlog
			 newSchedule.setBacklog(backlog);
		     
		     newSchedule.setSubjectPrefix(subject_prefix.toUpperCase());
		     
		     if(newSchedule.getId()==null) {
		    	//getting backlog Schedule Sequence and Incrementing it by 1
				  Integer backlogSequence = backlog.getScheduleSequence();
				  backlogSequence++;
				  backlog.setScheduleSequence(backlogSequence);
				  //setting edp code by combining subject prefix + backlogSequence
				  newSchedule.setEdpCode(subject_prefix.toUpperCase()+"-"+backlogSequence);
		     }
		     
			  
			//retrieve schedule data base on day and room number if there is a data
			  List<Schedule> schedules = getAllScheduleBasedOnDayAndRoomNumber(newSchedule.getDay(), newSchedule.getRoomNumber());
			   if(schedules != null) {
				   String ifTimeIsNotValid = null;
					   for(Schedule schedule : schedules) {
						 //validate time if it has no conflict
				           String time_in = schedule.getTimeIn();
						   String time_out = schedule.getTimeOut();
						   String checkTimeIn = newSchedule.getTimeIn();
						   String checkTimeOut = newSchedule.getTimeOut();
						   
						  
						try {
							//method that validates time
							 ifTimeIsNotValid = checkTimeService.isTimeBetweenTwoTime
					        (time_in, time_out, checkTimeIn,checkTimeOut);
						} catch (Exception e) {
							// TODO: handle exception
							e.getMessage();
						}
						
			                   List<String> listSched = Arrays.asList(ifTimeIsNotValid); 
			                   System.out.println("======================= Time is " +listSched );
			                   if(listSched.contains("true")) {
					                	throw new InvalidTimeException("Time is already taken, please select a valid start time and end time");	 
					                	 
			                   }
		             }//end for loop
				  
		}
			 return scheduleRepository.save(newSchedule);
		
			  

	}
	//function to retrieve schedule where day=day and roomnumber=roomnumber (String to datetime format)
    public List<Schedule> getAllScheduleBasedOnDayAndRoomNumber(String day,String roomNumber){
    	List<Schedule> schedules = scheduleRepository.findAllByDayAndRoomNumber(day, roomNumber);
    	return schedules;
    }
	
    
   
    public Schedule findScheduleByEdpCode(String EdpCode,String subjectPrefix) {
    	subjectService.getSubjectBySubNameSubjectPrefix(subjectPrefix.toUpperCase());
    	Schedule schedule = scheduleRepository.findOneByEdpCode(EdpCode.toUpperCase());
    	if(schedule == null) {
			throw new SubjectNotFoundException("Schedule with EDP Code "+EdpCode+" Does not Exist");
		}else if(!schedule.getSubjectPrefix().equals(subjectPrefix.toUpperCase())) {
			throw new SubjectNotFoundException("Schedule "+EdpCode+" does not exist in subject "+subjectPrefix);
		}
    	return schedule;
    }
	
    public Schedule updateScheduleOrAssignTeacherToSchedule(Schedule updateSchedule,String EdpCode,String subjectPrefix) {
    	Schedule schedule = findScheduleByEdpCode(EdpCode.toUpperCase(), subjectPrefix.toUpperCase());
    	schedule = updateSchedule;
    	return addSchedule(subjectPrefix, updateSchedule);
    }
    public void deleteSchedule(String EdpCode,String subjectPrefix) {
    	Schedule schedule=findScheduleByEdpCode(EdpCode.toUpperCase(), subjectPrefix.toUpperCase());
    	scheduleRepository.delete(schedule);
    }
    
}

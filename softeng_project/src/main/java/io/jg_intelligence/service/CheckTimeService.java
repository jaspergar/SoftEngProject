package io.jg_intelligence.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
@Service
public class CheckTimeService {
	public String isTimeBetweenTwoTime(String initialTime, String finalTime, 
		    String currentTimeIn,String currentTimeOut) throws ParseException {

//		    String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
//		    if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg)){
		        String valid = "false";
		        //Start Time
		        //all times are from java.util.Date
		        Date inTime = new SimpleDateFormat("hh:mm:ss aa").parse(initialTime);
		        Calendar calendar1 = Calendar.getInstance();
		        calendar1.setTime(inTime);

		        //Current Time in
		        Date checkTimeIn = new SimpleDateFormat("hh:mm:ss aa").parse(currentTimeIn);
		        Calendar calendar3 = Calendar.getInstance();
		        calendar3.setTime(checkTimeIn);
		        //Current time out
		        Date checkTimeOut = new SimpleDateFormat("hh:mm:ss aa").parse(currentTimeOut);
		        Calendar calendar4 = Calendar.getInstance();
		        calendar4.setTime(checkTimeOut);

		        //End Time
		        Date finTime = new SimpleDateFormat("hh:mm:ss aa").parse(finalTime);
		        Calendar calendar2 = Calendar.getInstance();
		        calendar2.setTime(finTime);

		        if (finalTime.compareTo(initialTime) < 0) 
		        {
		            calendar2.add(Calendar.DATE, 1);
		            calendar3.add(Calendar.DATE, 1);
		        }

		        java.util.Date actualTimeIn = calendar3.getTime();
		        java.util.Date actualTimeOut = calendar4.getTime();
		        
		      
	        	if ( (((actualTimeIn.after(calendar1.getTime()) || actualTimeIn.compareTo(calendar1.getTime()) == 0)  &&  actualTimeIn.before(calendar2.getTime())) 
	        			|| 
	        		(actualTimeOut.after(calendar1.getTime()) && (actualTimeOut.before(calendar2.getTime()) || actualTimeOut.compareTo(calendar2.getTime())==0) ))
	        		   ||
	        		   ((calendar1.getTime().after(actualTimeIn)|| calendar1.getTime().compareTo(actualTimeIn)==0) && calendar1.getTime().before(actualTimeOut) 
       				    || 
       				calendar2.getTime().after(actualTimeIn) && (calendar2.getTime().before(actualTimeOut) || calendar2.getTime().compareTo(actualTimeOut)==0))
	        			)  
		        {
	        		 
	        		
	        			valid = "true";
			            return valid;
	        		
		            
		        } else {
		           
		  
		        	return "false" ;
		        }
		        
		        
//		    }
//			return false;
		   
		}
}

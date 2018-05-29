package com.upa.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.upa.service.HandScanService;
import com.upa.service.UserService;
import com.upa.web.config.ApplicationProperties;
import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.TimeClocker;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.HandScanHeader;
import com.upa.web.model.entity.HandScanRecord;
import com.upa.web.model.entity.UserSalaryType;

import antlr.collections.List;

@Controller
public class HandScanController {
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	private static HandScanConstant handscanConstant = new HandScanConstant();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private HandScanHeader handscanheader = new HandScanHeader();
	private HandScanRecord handscanrecord = new HandScanRecord();
	private TimeClocker timeClocker = new TimeClocker();

	
	private AppUser appuserContext;

	private String currentDateStr;
	
	private HandScanService handscanservice;
	@Autowired(required=true)
	@Qualifier(value="handScanService")
	public void HandScanService(HandScanService ps){
		this.handscanservice = ps;
	}

	private UserService userservice;
	@Autowired(required=true)
	@Qualifier(value="userservice")
	public void UserService(UserService us){
		this.userservice = us;
	}
	
	
	@RequestMapping(value="/timeclock")
	public ModelAndView getData(@SessionAttribute("appuser") AppUser appuser){
		//forwards to timeclock.jsp at WEB-INF/pages/handscan/timeclock.jsp
		logger.trace("HandScanController.getData");
		ModelAndView model  = new ModelAndView(); 		
		//User's profile
		UserSalaryType userSalaryType = this.userservice.getUserSalaryType(appuser.getUserSeq());
		if(userSalaryType == null){
			model  = new ModelAndView("profiles/appuser/salarytype");
			userSalaryType = new UserSalaryType();
			model.addObject("userSalaryType", userSalaryType);
			model.addObject("appuser", appuser);
			return model;
		}else{				
			return forwardToTimeClockUI(appuser,userSalaryType);			
		}
	}
	
	@RequestMapping(value="/timeclockrecord")
	public ModelAndView forwardToRecord(@SessionAttribute("appuser") AppUser appuser){
		//forwards to timeclock.jsp at WEB-INF/pages/handscan/timeclock.jsp
		logger.trace("HandScanController-forwardToRecord");
		ModelAndView mv  = new ModelAndView(); 		
		
		handscanheader = this.handscanservice.getHandScanOfTerm(getCurrentTime(), appuser.getUserId());
		if(handscanheader != null){
			mv = new ModelAndView("handscan/handScanResult");
			mv.addObject("handscanheader", this.handscanheader);
			mv.addObject("msg", "The HandScan has been submitted.");
			return mv;		
			}else{
			//The result not found for this user
			return null;
		}
	}
	
	
	@RequestMapping("/submitUserPayPeriod")
	public ModelAndView submitUserPayPeriod(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute UserSalaryType userSalaryType){
		logger.trace("HandScanController-submitUserPayPeriod");
		userSalaryType.setAppUser(appuser);
		userSalaryType.setTzCode("CDS");
		userSalaryType.setLastDate(addTimePortion(userSalaryType.getLastDate()));		
		//Insert the record to the database
		String status = userservice.addUserSalaryType(userSalaryType);
		if(status.equals("SUCCESS")){			
			return forwardToTimeClockUI(appuser,userSalaryType);
		}else{
			return null;
		}
	}	
	
	private ModelAndView forwardToTimeClockUI(AppUser appuser, UserSalaryType userSalaryType){
		ModelAndView model  = new ModelAndView("handscan/timeclock"); 
		
		String userId = appuser.getUserId();

		handscanheader = this.handscanservice.getHandScanOfTerm(getCurrentTime(), userId);
		
		if(handscanheader != null && handscanheader.getHeaderSeq() != null){
			setHandscanheader(handscanheader);
		}else{
			handscanheader = new HandScanHeader();
			handscanheader.setFirstDate(userSalaryType.getFirstDate());
			handscanheader.setLastDate(userSalaryType.getLastDate());
			handscanheader.setAppuser(appuser);
			handscanheader = adjustDateRange(handscanheader,getCurrentTime(),userSalaryType.getPayPeriodType());
		}
		
		/*Clockin and out information*/
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		model.addObject("getCurrentDate",dateFormat.format(getCurrentTime()));
		DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		model.addObject("getCurrentTime", timeFormat.format(getCurrentTime()));
					
		timeClocker = new TimeClocker();		
		timeClocker.setFirstDate(handscanheader.getFirstDate());
		timeClocker.setLastDate(handscanheader.getLastDate());
		
		model.addObject("timeclocker", timeClocker);
		model.addObject("handScanRecord", new HandScanRecord());		
		
		return model;
	}
	
	private Boolean isSameDate(Date scanDate, String UiEnteredDate){
		try{
			SimpleDateFormat formatterD = new SimpleDateFormat("MM/dd/yyyy");
			Date checkinDate = formatterD.parse(UiEnteredDate);
			if( checkinDate.equals(scanDate) ){
				return Boolean.TRUE;
			}else{
				return Boolean.FALSE;
			}
		}catch(Exception e){
			return null;
		}
	}

	
	@RequestMapping("/submit")
	public ModelAndView submit(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute TimeClocker timeclocker){
		String status = null;
		ModelAndView mv = new ModelAndView();
		boolean isRecordForTheDayFound = false;
		logger.trace("HandScanController - submit()");
		if(getHandscanheader()!= null){
			//Header is already in DB.  Search Records for the day
			if(getHandscanheader().getHandscanrecords() != null){
				for(HandScanRecord hsr : getHandscanheader().getHandscanrecords()){
					if(isSameDate(hsr.getScanDate(), timeclocker.getScanDateStr() )){
						isRecordForTheDayFound = true;
						if(hsr.getScanInDateTime()!=null && timeclocker.getClockInOut().equals("I") ){
							//This is invalid.  It is already checked in.
							mv.addObject("msg", "Already exists the Clock In for "+ timeclocker.getScanDateStr() );
							return mv;								
						}else if(hsr.getScanOutTime()!=null && timeclocker.getClockInOut().equals("O") ){
							//This is invalid.  It is already checked in.
							mv.addObject("msg", "Already exists the Clock Out for "+ timeclocker.getScanDateStr() );
							return mv;
						}

						setHandscanrecord(updateHandScanRecordFromUI(timeclocker, hsr));								

						break;
					}
				}
			}
		}

		if(!isRecordForTheDayFound){
			//Create Record
			handscanrecord = new HandScanRecord();
			ArrayList<HandScanRecord> hsrs = new ArrayList<HandScanRecord>();
			setHandscanrecord(updateHandScanRecordFromUI(timeclocker, handscanrecord));
			handscanrecord.setHandScanHeader(getHandscanheader());
			hsrs.add(handscanrecord);
			handscanheader.setHandscanrecords(hsrs);
		}

		status = this.handscanservice.addHandScan(handscanrecord, getHandscanheader());

		if(status==null){

			handscanheader = handscanservice.getHandScanHeaderById(handscanheader.getHeaderSeq());
			mv = new ModelAndView("handscan/handScanResult");
			mv.addObject("handscanheader", this.handscanheader);
			mv.addObject("msg", "The HandScan has been submitted.");
			return mv;
		}else{
			mv.addObject("msg", status);
			return mv;
		}
	}
	
	private HandScanRecord updateHandScanRecordFromUI(TimeClocker tc, HandScanRecord handscanrecord){
		SimpleDateFormat formatterD = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatterT = new SimpleDateFormat("hh:mm a");
		try {
			if(handscanrecord.getScanDate() == null){
				handscanrecord.setScanDate(formatterD.parse(tc.getScanDateStr()));
			}
			if(tc.getClockInOut().equals("I")){
				handscanrecord.setScanInTime(formatterT.parse(tc.getScanTimeStr()));
			}else if(tc.getClockInOut().equals("O")){
				handscanrecord.setScanOutTime(formatterT.parse(tc.getScanTimeStr()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return handscanrecord; 
	}
	
	//Tools	
	private Date addTimePortion(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		c.add(Calendar.SECOND, -1);
		date=c.getTime();
		return date;
	}
	
    private HandScanHeader adjustDateRange(HandScanHeader handscanheader, Date currentDate, String payPeriodType){
     	if(handscanheader.getFirstDate().before(currentDate) && handscanheader.getLastDate().after(currentDate)){
    		return handscanheader;
    	}else{
    		Date firstDay = handscanheader.getFirstDate();
    		Date lastDay = handscanheader.getLastDate();
    		firstDay = incrementDate(firstDay, payPeriodType);
    		lastDay = incrementDate(lastDay, payPeriodType);
    		
    		handscanheader.setFirstDate(firstDay);
    		handscanheader.setLastDate(lastDay);
    		
    		return adjustDateRange(handscanheader, currentDate, payPeriodType);
    	}
    }
    
    private Date incrementDate(Date date, String payPeriodType){
  		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		switch(payPeriodType){
			case "1":
				c.add(Calendar.MONTH, 1);
			break;
			
			case "2":
	    		c.add(Calendar.DATE, 15);
			break;
			
			case "3":
	    		c.add(Calendar.DATE, 14);
			break;
			
			case "4":
	    		c.add(Calendar.DATE, 7);
			break;
			
			case "5":
	    		c.add(Calendar.DATE, 1);
			break;
		}
		
		return c.getTime();
    }
		
	private Date getCurrentTime(){
		Date date = new Date();
		return date;
	}

	public String getCurrentDateStr() {
		return currentDateStr;
	}

	public void setCurrentDateStr(String currentDateStr) {
		this.currentDateStr = currentDateStr;
	}

	public HandScanRecord getHandscanrecord() {
		return handscanrecord;
	}

	public void setHandscanrecord(HandScanRecord handscanrecord) {
		this.handscanrecord = handscanrecord;
	}

	public HandScanHeader getHandscanheader() {
		return handscanheader;
	}

	public void setHandscanheader(HandScanHeader handscanheader) {
		this.handscanheader = handscanheader;
	}

	public AppUser getAppuserContext() {
		return appuserContext;
	}

	public void setAppuserContext(AppUser appuserContext) {
		this.appuserContext = appuserContext;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
}

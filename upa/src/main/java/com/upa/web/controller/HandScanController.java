package com.upa.web.controller;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.type.descriptor.java.TimeZoneTypeDescriptor.TimeZoneComparator;
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
import com.upa.service.logger.ILoggerService;
import com.upa.service.logger.LoggerServiceImpl;
import com.upa.web.config.ApplicationProperties;
import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.TimeClocker;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.HandScanHeader;
import com.upa.web.model.entity.HandScanRecord;
import com.upa.web.model.entity.UserSalaryType;

@Controller
public class HandScanController {
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	private static HandScanConstant handscanConstant = new HandScanConstant();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private HandScanHeader handscanheader = new HandScanHeader();
	private HandScanRecord handscanrecord = new HandScanRecord();
	
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
		
		TimeClocker hs = new TimeClocker();
		
		Integer userSeq = appuser.getUserSeq();
		
		//User's profile
		UserSalaryType userSalaryType = this.userservice.getUserSalaryType(userSeq);
		
		if(userSalaryType == null){
			model  = new ModelAndView("profiles/appuser/salarytype");
			userSalaryType = new UserSalaryType();
			//userSalaryType.setAppUser(appuser);
			model.addObject("userSalaryType", userSalaryType);
			model.addObject("appuser", appuser);
			return model;
		}else{
			model  = new ModelAndView("handscan/timeclock"); 
			
			String userId = appuser.getUserId();
	
			HandScanHeader hsh = this.handscanservice.getHandScanOfTerm(getCurrentTime(), userId);
			
			if(hsh != null && hsh.getHeaderSeq() != null){
				System.out.println("header ID: "+ hsh.getHeaderSeq());
				setHandscanheader(hsh);
			}
			
			/*Clockin and out information*/
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			//setCurrentDateStr(dateFormat.format(getCurrentTime()));
			model.addObject("getCurrentDate",dateFormat.format(getCurrentTime()));
			DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
			model.addObject("getCurrentTime", timeFormat.format(getCurrentTime()));
						
			//setHandscanheaderList(this.handscanservice.getHandScanList());
			model.addObject("timeclocker", new TimeClocker());
			model.addObject("handScanRecord", new HandScanRecord());		
			
			return model;
		}
	}
	
	@RequestMapping("/payPeriodSubmit")
	public ModelAndView payPeriodSubmit(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute UserSalaryType userSalaryType){
		logger.trace("HandScanController-payPeriodSubmit");
		userSalaryType.setAppUser(appuser);
		userSalaryType.setTzCode("CDS");
		//userSalaryType.setUserSeq(appuser.getUserSeq());
		if(userservice.addUserSalaryType(userSalaryType).equals("SUCCESS")){
			ModelAndView model  = new ModelAndView("handscan/timeclock"); 
			
			String userId = appuser.getUserId();
	
			HandScanHeader hsh = this.handscanservice.getHandScanOfTerm(getCurrentTime(), userId);
			
			if(hsh != null && hsh.getHeaderSeq() != null){
				System.out.println("header ID: "+ hsh.getHeaderSeq());
				setHandscanheader(hsh);
			}
			
			/*Clockin and out information*/
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			//setCurrentDateStr(dateFormat.format(getCurrentTime()));
			model.addObject("getCurrentDate",dateFormat.format(getCurrentTime()));
			DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
			model.addObject("getCurrentTime", timeFormat.format(getCurrentTime()));
						
			//setHandscanheaderList(this.handscanservice.getHandScanList());
			model.addObject("timeclocker", new TimeClocker());
			model.addObject("handScanRecord", new HandScanRecord());		
			
			return model;
		}else{
			return null;
		}
	}
	

	
	@RequestMapping("/submit")
	public ModelAndView submit(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute TimeClocker hs){
		String status = null;
		System.out.println("HandScanController @ submit()");
		System.out.println(getHandscanheader().getHeaderSeq());
		System.out.println(hs.getScanDateStr());
		System.out.println(hs.getScanTimeStr());
	
		if(getHandscanrecord() != null && getHandscanrecord().getRecordSeq() != null) {
			HandScanHeader handscanheader = new HandScanHeader();
			HandScanRecord handscanrecord = new HandScanRecord();
			setHandscanrecord(setHandScanRecordFromUI(hs, handscanrecord));
			status = this.handscanservice.addHandScanRecordUpdateHeader(getHandscanrecord(), getHandscanheader());
		}else{
			status = this.handscanservice.addHandScan(getHandscanrecord(), getHandscanheader());
		}
		
		ModelAndView mv = new ModelAndView("timeclock/handScanResult");
				
		if(status==null){
			mv.addObject("handScansList", this.handscanservice.getHandScanList());
			mv.addObject("msg", "The HandScan has been submitted.");
			return mv;
		}else{
			mv.addObject("msg", status);
			return mv;
		}
	}
	
	
	private HandScanHeader setHandScanHeaderFromUI(TimeClocker tc, HandScanHeader hs){
		//tc.
		return null;
	}
	
	private HandScanRecord setHandScanRecordFromUI(TimeClocker tc, HandScanRecord handscanrecord){
		SimpleDateFormat formatterD = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatterT = new SimpleDateFormat("hh:mm a");
		try {
			handscanrecord.setScanDate(formatterD.parse(tc.getScanDateStr()));
			if(tc.getClockInOut().equals("I")){
				handscanrecord.setScanInTime(formatterT.parse(tc.getScanTimeStr()));
			}else{
				handscanrecord.setScanOutTime(formatterT.parse(tc.getScanTimeStr()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return handscanrecord; 
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

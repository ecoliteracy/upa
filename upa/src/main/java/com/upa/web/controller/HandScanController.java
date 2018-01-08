package com.upa.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.upa.web.config.ApplicationProperties;
import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.HandScan;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.HandScanHeader;
import com.upa.web.model.entity.HandScanRecord;
import com.upa.web.service.HandScanService;

@Controller
public class HandScanController {
	
	private HandScanService handscanservice;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	private static HandScanConstant handscanConstant = new HandScanConstant();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private HandScanHeader handscanheader = new HandScanHeader();
	private HandScanRecord handscanrecord = new HandScanRecord();
	//private HandScan handscan = new HandScan();
	
	private AppUser appuserContext;

	private String currentDateStr;
	
	@Autowired(required=true)
	@Qualifier(value="handScanService")
	public void HandScanService(HandScanService ps){
		this.handscanservice = ps;
	}
	
	@RequestMapping(value="/handscan")
	public ModelAndView getData(@SessionAttribute("appuser") AppUser appuser){
		//forwards to handscan.jsp
		ModelAndView model  = new ModelAndView("handscan/handscan");
		
		HandScan hs = new HandScan();
		
		String userId = appuser.getUserId();
		
		HandScanHeader hsh = this.handscanservice.getHandScanOfTerm(getCurrentTime(), userId);
		
		if(hsh != null && hsh.getHeaderId() != null){
			System.out.println("header ID: "+ hsh.getHeaderId());
			setHandscanheader(hsh);
		}else{
			System.out.println("header ID: NULL - new HandScanHeader()");
		}
		
		/*Clockin and out information*/
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//setCurrentDateStr(dateFormat.format(getCurrentTime()));
		model.addObject("getCurrentDate",dateFormat.format(getCurrentTime()));
		DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		model.addObject("getCurrentTime", timeFormat.format(getCurrentTime()));
		
		
		//setHandscanheaderList(this.handscanservice.getHandScanList());
		model.addObject("handscan", new HandScan());
		model.addObject("handScanRecord", new HandScanRecord());		
		
		return model;
	}
	

	
	@RequestMapping("/submit")
	public ModelAndView submit(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute HandScan hs){
		
		String status = null;
		
		System.out.println("HandScanController @ submit()");
		System.out.println(getHandscanheader().getHeaderId());
		System.out.println(hs.getFirstDate());
		System.out.println(hs.getLastDate());
		
		System.out.println();
		
		
		if(getHandscanheader() != null && getHandscanheader().getHeaderId() != null){
			//setHandscanrecord(this.handscanservice.getMatchingHandScanRecord(getHandscanheader(), hs.getScanDateStr()));
		}
		
		if(getHandscanrecord() != null && getHandscanrecord().getRecordId() != null) {
			System.out.println("[1]======== FOUND RECORD ======= ");
			System.out.println("getHandscanrecord().getRecordId() :  "+ getHandscanrecord().getRecordId());

			status = this.handscanservice.addHandScanRecordUpdateHeader(getHandscanrecord(), getHandscanheader());
		}else{
			System.out.println("[2]======== NOT FOUND RECORD ======= ");
			
			status = this.handscanservice.addHandScan(getHandscanrecord(), getHandscanheader());
		}
		
		ModelAndView mv = new ModelAndView("handscan/handScanResult");
				
		if(status==null){
			mv.addObject("handScansList", this.handscanservice.getHandScanList());
			mv.addObject("msg", "The HandScan has been submitted.");
			return mv;
		}else{
			mv.addObject("msg", status);
			return mv;
		}
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
}

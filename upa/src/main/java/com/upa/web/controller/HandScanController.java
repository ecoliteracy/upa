package com.upa.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.upa.web.config.ApplicationProperties;
import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.HandScanHeader;
import com.upa.web.model.HandScanRecord;
import com.upa.web.service.HandScanService;

@Controller
public class HandScanController {
	
	private HandScanService handscanservice;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	private static HandScanConstant handscanConstant = new HandScanConstant();
	
	private HandScanHeader handscanheader = new HandScanHeader();
	private HandScanRecord handscanrecord = new HandScanRecord();
	
	private String currentDateStr;
	
	@Autowired(required=true)
	@Qualifier(value="handScanService")
	public void HandScanService(HandScanService ps){
		this.handscanservice = ps;
	}
	
	@RequestMapping(value="/handscan")
	public ModelAndView getData(){
		
		ModelAndView model  = new ModelAndView("handscan/handscan");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		setCurrentDateStr(dateFormat.format(getCurrentTime()));
		model.addObject("getCurrentDate",dateFormat.format(getCurrentTime()));
		
		DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		model.addObject("getCurrentTime", timeFormat.format(getCurrentTime()));
		
		HandScanHeader hsh = this.handscanservice.getHandScanOfTerm(getCurrentTime());
		System.out.println("+++++++++++++++");
		if(hsh != null && hsh.getHeaderId() != null){
			System.out.println("header ID: "+ hsh.getHeaderId());
			setHandscanheader(hsh);
		}else{
			System.out.println("header ID: NULL - new HandScanHeader()");
			setHandscanheader(new HandScanHeader());
		}
		System.out.println("+++++++++++++++");
		
		//setHandscanheaderList(this.handscanservice.getHandScanList());
		model.addObject("handScanHeader", getHandscanheader());
		
		model.addObject("handscanEntity", new HandScanRecord());
		
		return model;
	}
	
	@RequestMapping("/submit")
	public ModelAndView submit(@ModelAttribute HandScanRecord hs){
		
		String status = null;
		setHandscanrecord(hs);
		
		if(getHandscanheader() != null && getHandscanheader().getHeaderId() != null){
			setHandscanrecord(this.handscanservice.getMatchingHandScanRecord(getHandscanheader(), hs.getScanDateStr()));
		}
		
		if(getHandscanrecord() != null && getHandscanrecord().getRecordId() != null) {
			System.out.println(" ======== FOUND RECORD ======= ");
			System.out.println("getHandscanrecord().getRecordId() :  "+ getHandscanrecord().getRecordId());
			getHandscanrecord().setScanDateStr(hs.getScanDateStr());
			getHandscanrecord().setScanTimeStr(hs.getScanTimeStr());
			getHandscanrecord().setType(hs.getType());
			status = this.handscanservice.addHandScanRecordUpdateHeader(getHandscanrecord(), getHandscanheader());
		}else{
			System.out.println(" ======== NOT FOUND RECORD ======= ");
			setHandscanrecord(hs);
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
	

}

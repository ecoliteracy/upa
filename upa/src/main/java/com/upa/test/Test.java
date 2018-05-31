package com.upa.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.upa.service.HandScanServiceImpl;
import com.upa.service.UserServiceImpl;
import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.HandScanHeader;
import com.upa.web.model.entity.HandScanRecord;
//
public class Test {
	
	private static HandScanServiceImpl handscanservice = new HandScanServiceImpl();
	private static UserServiceImpl userservice = new UserServiceImpl();

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testLogin();
		//testTimeAddTime();
		System.out.println(125%60);
	}
	
	private static void testLogin(){
		AppUser l = new AppUser();
		l.setUserId("KIWASAKI");
		l.setUserPassword("welcome1");
	}
	
	private static void testTimeAddTime(){
		AppUser appuser = new AppUser();
		appuser.setUserId("KIWASAKI");
		appuser.setUserPassword("welcome1");
		UserValidationResult rs = userservice.isValidUserPassword(appuser);
		appuser = rs.getAppuser();


		
		HandScanHeader handscanheader = handscanservice.getHandScanOfTerm(getCurrentTime(), appuser.getUserId());
	}
	
	private static Date getCurrentTime(){
		Date date = new Date();
		return date;
	}
	
}

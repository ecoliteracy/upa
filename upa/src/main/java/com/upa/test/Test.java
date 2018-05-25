package com.upa.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.upa.service.HandScanServiceImpl;
import com.upa.service.UserServiceImpl;
import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.entity.AppUser;
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
		testLogin();
	}
	
	private static void testLogin(){
		AppUser l = new AppUser();
		l.setUserId("KIWASAKI");
		l.setUserPassword("welcome1");
	}
	
}

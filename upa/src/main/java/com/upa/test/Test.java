package com.upa.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.HandScanRecord;
import com.upa.web.service.HandScanServiceImpl;

public class Test {
	
	private static HandScanServiceImpl handscanservice = new HandScanServiceImpl();

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testMethods1();
	}
	
	private static void testMethods1(){
		HandScanConstant c = new HandScanConstant();
		HandScanRecord h = new HandScanRecord();
		h.setType("I");
		h.setScanDateStr("03/10/2017"); //MM/dd/yyyy
		h.setScanTimeStr("9:54 am"); //hh:mm a
		
		
		SimpleDateFormat formatterD = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatterT = new SimpleDateFormat("hh:mm a");
		try {
			h.setScanDate(formatterD.parse(h.getScanDateStr()));
			if(h.getType().equals("I")){
				h.setScanInTime(formatterT.parse(h.getScanTimeStr()));
			}else{
				h.setScanOutTime(formatterT.parse(h.getScanTimeStr()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		h.setType("O");
		h.setScanDateStr("03/10/2017"); //MM/dd/yyyy
		h.setScanTimeStr("10:55 am"); //hh:mm a
		try {
			h.setScanDate(formatterD.parse(h.getScanDateStr()));
			if(h.getType().equals("I")){
				h.setScanInTime(formatterT.parse(h.getScanTimeStr()));
			}else{
				h.setScanOutTime(formatterT.parse(h.getScanTimeStr()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		handscanservice.getHourInRecord(h);
	}
}

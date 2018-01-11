package com.upa.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upa.web.constant.HandScanConstant;
import com.upa.web.dao.HandScanDaoImpl;
import com.upa.web.model.entity.HandScanHeader;
import com.upa.web.model.entity.HandScanRecord;

@Service
public class HandScanServiceImpl implements HandScanService{

	private static List<HandScanRecord> handScansList;
	
	private static HandScanConstant handscanConstant = new HandScanConstant();
	
	private static HandScanValidation validation = new HandScanValidation();
	
	private HandScanDaoImpl handscandao;
	
	@Autowired	
	public void handscandao(HandScanDaoImpl hsDAO){
		this.handscandao = hsDAO;
	}
		
	public HandScanServiceImpl(){
		handScansList = new ArrayList<HandScanRecord>();
	}
	
	public List<HandScanHeader> getHandScanList(){
		return handscandao.getHandScan();
	}
	
	
	
	
	
	@Transactional
	public String addHandScan(HandScanRecord hr, HandScanHeader h){
		handScansList = new ArrayList<HandScanRecord>();
		SimpleDateFormat formatterD = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatterT = new SimpleDateFormat("hh:mm a");
		try {
			hr.setScanDate(formatterD.parse(hr.getScanDateStr()));
			if(hr.getType().equals("I")){
				hr.setScanInTime(formatterT.parse(hr.getScanTimeStr()));
			}else{
				hr.setScanOutTime(formatterT.parse(hr.getScanTimeStr()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(hr != null && hr.getRecordId() != null){
			//update case
			EntityUtils.setupAuditTrail(hr, Boolean.FALSE);
		}else{
			//insert case
			EntityUtils.setupAuditTrail(hr, Boolean.TRUE);			
		}
		
		//
		System.out.println("[1]=====HandScanServiceImpl=====");
		System.out.println(hr.getTz_code());
		System.out.println(hr.getLast_modified_date());
		System.out.println(hr.getCreated_date());
		
		/*
		if(h != null && h.getHeaderId() != null){
			
			EntityUtils.setupAuditTrail(h, Boolean.FALSE);
		}else{
			try {
				h = createHandScanHeaderObj(hr.getScanDate(), handscanConstant.FIRST_DATE);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			handScansList.add(hr);
			h.setHandscanrecords(handScansList);
			h.setTermType(handscanConstant.TERM_GOAL);
			EntityUtils.setupAuditTrail(h, Boolean.TRUE);
		}
		*/
		
		System.out.println("[2]=====HandScanServiceImpl=====");
		System.out.println(h.getTz_code());
		System.out.println(h.getLast_modified_date());
		System.out.println(h.getCreated_date());
		
		hr.setParticipationTime(getHourInRecord(hr));
		if(hr.getParticipationTime()!=null){
			h.setTotalHour(hr.getParticipationTime().getTime());	
		}else{
			h.setTotalHour(0L);
		}
		
		long timeTermGoal = getHourMin(handscanConstant.TERM_GOAL+":00");
		if(hr.getParticipationTime()!=null){
			h.setRemainingHour(getSubtrHrMin( timeTermGoal, hr.getParticipationTime().getTime()));
		}else{
			h.setRemainingHour(0L);
		}
		
		System.out.println("[3]=====HandScanServiceImpl=====");
		System.out.println(h.getAppuser().getUserId());
		System.out.println(h.getFirstDate());
		System.out.println(h.getLastDate());
		System.out.println(h.getRemainingHour());
		System.out.println(h.getTermType());
		System.out.println(h.getTotalHour());
		hr.setHandScanHeader(h);
		
		handscandao.saveHandscan(hr);
		
		handScansList.add(hr);
		
		return null;
	}
	
	private long getHourMin(String hourMinStr){
		System.out.println("getHourMin: "+ hourMinStr);
		SimpleDateFormat formatterT = new SimpleDateFormat("hh:mm");
		Date hrMin = null;
		try{
			hrMin = formatterT.parse(hourMinStr);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return hrMin.getTime();
	}
	
	private long dateHrMinConvertToLong(Date a){
		return a.getTime();
	}
	
	private long getSubtrHrMin(long hm1, long hm2){
		if(hm1==0L || hm2==0L)
			return 0L;
		long diff = hm1 - hm2;
		System.out.println("getSubtrHrMin - diff: "+ diff);
		System.out.println("hm1: "+ hm1);
		System.out.println("hm2: "+ hm2);
		
		long diffHours = diff/(60*60*1000);//%24;
		long diffMinutes = diff / (60 * 1000) % 60;
				
		return diffHours + diffMinutes;
	}
	
	
	@Transactional
	public String addHandScanRecordUpdateHeader(HandScanRecord hr, HandScanHeader h){
		handScansList = new ArrayList<HandScanRecord>();
		SimpleDateFormat formatterD = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatterT = new SimpleDateFormat("hh:mm a");
		try {
			if(hr.getType().equals("I")){
				hr.setScanDate(formatterD.parse(hr.getScanDateStr()));
				hr.setScanInTime(formatterT.parse(hr.getScanTimeStr()));
			}else{
				hr.setScanDate(formatterD.parse(hr.getScanDateStr()));
				hr.setScanOutTime(formatterT.parse(hr.getScanTimeStr()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(hr != null && hr.getRecordId() != null){
			//update case
			EntityUtils.setupAuditTrail(hr, Boolean.FALSE);
		}else{
			//insert case
			EntityUtils.setupAuditTrail(hr, Boolean.TRUE);
		}
		
		if(hr.getHandScanHeader() != null && hr.getHandScanHeader().getHeaderId() != null){
			EntityUtils.setupAuditTrail(hr.getHandScanHeader(), Boolean.FALSE);
		}else{
			EntityUtils.setupAuditTrail(hr.getHandScanHeader(), Boolean.TRUE);
		}
		
		hr.setParticipationTime(getHourInRecord(hr));
		
		//Validation
		String errorMsg = validation.checkInOutTime(hr);
		if(errorMsg != null){
			return errorMsg;
		}

		handscandao.saveHandscan(hr);
		
		//Post Process - Update the Header Information 
		
		saveHeaderInfo(hr,h);
		
		//HandScanHeader handH = getHandScanOfTerm(hr.getScanDate());
		//handscandao.saveHandscanHeader(handH);
		//handScansList = handH.getHandscanrecords();
		
		return null;
	}

	public HandScanHeader getHandScanOfTerm(Date dt, String userId) {
		HandScanHeader h = handscandao.getHandScanOfTerm(dt, userId);
		if(h != null && h.getHandscanrecords() != null && h.getHandscanrecords().size() > 0){
			h.setTotalHour(getSumOfParticipation(h.getHandscanrecords()));
			h.setRemainingHour(getSubtrHrMin(getHourMin(handscanConstant.TERM_GOAL+":00"), h.getTotalHour()));
			System.out.println(h.getTotalHour());
			System.out.println(h.getRemainingHour());
		}
		return h;
	}
	
	private void saveHeaderInfo(HandScanRecord hr, HandScanHeader h) {
		List<Date> times = handscandao.getParticipateTime(h.getHeaderId());
		long totalHr = 0L; 
		System.out.println("saveHeaderInfo: "+ times);
		long hT = 0L;
		if(times != null && times.size() > 0){
			System.out.println("times is not null");
			for(Date t: times){
				System.out.println("saveHeaderInfo: "+t.getTime());
				hT = t.getTime() + hT;
			}

			System.out.println("hT: "+hT);
			long th = hT / (60*60*1000) %24;
			long tm = hT / (60 * 1000) % 60;
			String timeStr = String.valueOf(th)+":"+String.valueOf(tm);
			
			totalHr = th + tm;
		}else{
			System.out.println("times is null");
			if(hr.getParticipationTime() != null){
				System.out.println("Take it from ParticipationTime");
				totalHr  = hr.getParticipationTime().getTime();
			}else{
				return;
			}
		}

		h.setTotalHour(totalHr);
		h.setRemainingHour(getSubtrHrMin(getHourMin(handscanConstant.TERM_GOAL+":00"), h.getTotalHour()));
		System.out.println(h.getTotalHour());
		System.out.println(h.getRemainingHour());
		handscandao.saveHandscanHeader(h);
	}
	/*
	private void saveHeaderInfo(HandScanRecord hr, HandScanHeader h) {
		long hT = 0L;
		long th = 0L;
		long tm = 0L;
		
		if(h.getTotalHour() != null){
			hT = h.getTotalHour().getTime();	
		}
		
		if(hr.getScanInDateTime() != null && hr.getScanOutTime() != null){
				if(hr.getParticipationTime() != null){
					long t = hr.getParticipationTime().getTime() + hT;
					th = t / (60*60*1000) %24;
					tm = t / (60 * 1000) % 60;
				}
				String timeStr = String.valueOf(th)+":"+String.valueOf(tm);
				SimpleDateFormat formatterT = new SimpleDateFormat("hh:mm");
				Date totalHr = null;
				try {
					totalHr = formatterT.parse(timeStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				h.setTotalHour(totalHr);
				h.setRemainingHour(getSubtrHrMin(getHourMin(handscanConstant.TERM_GOAL+":00"), h.getTotalHour()));
				System.out.println(h.getTotalHour());
				System.out.println(h.getRemainingHour());
				handscandao.saveHandscanHeader(h);	
		}
	}*/

	@Override
	public HandScanRecord getMatchingHandScanRecord(HandScanHeader hd, String dateComp) {
		try {
			if(hd != null && hd.getHeaderId() != null){
				if(hd.getHandscanrecords()!=null && hd.getHandscanrecords().size() > 0){
					for(HandScanRecord hsr : hd.getHandscanrecords()){
						if(hsr.getScanDate() != null){
							SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
							Date date1;
							date1 = sdf.parse(dateComp);
							Date date2 = hsr.getScanDate();
							if(sdf.format(date1).equals(sdf.format(date2))){
								return hsr;
							}	
						}
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HandScanRecord();
	}
	
	private long getSumOfParticipation(List<HandScanRecord> hsr){
		long time = 0L;
		if(hsr!= null && hsr.size() > 0){
			for(HandScanRecord r: hsr){
				if(r.getParticipationTime() != null){
					long t = r.getParticipationTime().getTime() + time;
				}
			}
		}
		long th = time / (60 * 60 * 1000);// % 24;
		long tm = time / (60 * 1000) % 60;
		return th + tm;
	}
	
	public HandScanHeader createHandScanHeaderObj(Date inputDate, String FIRST_DATE) throws ParseException{
		HandScanHeader h = new HandScanHeader();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date firstDate = format.parse(FIRST_DATE);

        Calendar cal = Calendar.getInstance();
        cal.setTime(firstDate);
        cal.add(Calendar.DATE, handscanConstant.TERM);
        Date lastDate = cal.getTime();

        
        if((inputDate.equals(firstDate) || inputDate.after(firstDate))
        		&& (inputDate.equals(lastDate) || inputDate.before(lastDate))){
        	h.setFirstDate(firstDate);
        	h.setLastDate(lastDate);
        	return h;
        }else{
        	cal.setTime(lastDate);
        	cal.add(Calendar.DATE, 1);
        	String nextFirstDate = format.format(cal.getTime());
        	return createHandScanHeaderObj(inputDate, nextFirstDate);
        }
	}
	
	public Date getHourInRecord(HandScanRecord hsr){
		Date diffTime = null;
		SimpleDateFormat formatterT = new SimpleDateFormat("hh:mm");
		if(hsr.getScanInDateTime() != null && hsr.getScanOutTime() != null){
			try {
				long diff = hsr.getScanOutTime().getTime() - hsr.getScanInDateTime().getTime();
				long diffHours = diff/(60*60*1000) %24;
				long diffMinutes = diff / (60 * 1000) % 60;
				String diffStr = String.valueOf(diffHours)+":"+String.valueOf(diffMinutes);
				diffTime = formatterT.parse(diffStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return diffTime;
	}
}

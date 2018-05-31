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
		
		//Validation
		String errorMsg = validation.checkInOutTime(hr);
		if(errorMsg != null){
			return errorMsg;
		}
		
		if(h != null && h.getHeaderSeq() != null){
			//update case
			EntityUtils.setupAuditTrail(h, Boolean.FALSE);
		}else{
			//insert case
			EntityUtils.setupAuditTrail(h, Boolean.TRUE);			
		}
		
		if(hr != null && hr.getRecordSeq() != null){
			//update case
			EntityUtils.setupAuditTrail(hr, Boolean.FALSE);
		}else{
			//insert case
			EntityUtils.setupAuditTrail(hr, Boolean.TRUE);			
		}
		
		h.setTotalHour(getSumOfParticipation(h.getHandscanrecords()));
		h.setRemainingHour(subtrMinsFromMins(h.getWorkingHourInMin(), h.getTotalHour()));			
		hr.setHandScanHeader(h);
		
//		handscandao.saveHandscanHeader(h);
		handscandao.saveHandscan(hr);
		
		handScansList.add(hr);
		
		return null;
	}
	

	private Date dateHrMinLongToDate(long a){
		Date time = new Date();
		int timeInMilliSecond = 0;
		int th = timeInMilliSecond / (60 * 60 * 1000);
		int tm = timeInMilliSecond / (60 * 1000) % 60;
		time.setHours(th);;
		time.setMinutes(tm);
		return time;
	}
	
	
	private Integer subtrMinsFromMins(Integer min1, Integer min2){
		return min1 - min2;
	}
	
	@Transactional
	public String addHandScanRecordUpdateHeader(HandScanRecord hr, HandScanHeader h){
		
		if(h != null && h.getHeaderSeq() != null){
			//update case
			EntityUtils.setupAuditTrail(h, Boolean.FALSE);
		}else{
			//insert case
			EntityUtils.setupAuditTrail(h, Boolean.TRUE);
		}
		
		if(hr != null && hr.getRecordSeq() != null){
			//update case
			EntityUtils.setupAuditTrail(hr, Boolean.FALSE);
		}else{
			//insert case
			EntityUtils.setupAuditTrail(hr, Boolean.TRUE);
		}
		
		if(hr.getHandScanHeader() != null && hr.getHandScanHeader().getHeaderSeq() != null){
			EntityUtils.setupAuditTrail(hr.getHandScanHeader(), Boolean.FALSE);
		}else{
			EntityUtils.setupAuditTrail(hr.getHandScanHeader(), Boolean.TRUE);
		}		
	
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
			h.setRemainingHour(subtrMinsFromMins(h.getWorkingHourInMin(), h.getTotalHour()));			

		}
		return h;
	}
	
	private void saveHeaderInfo(HandScanRecord hr, HandScanHeader h) {
		List<Date> times = handscandao.getParticipateTime(h.getHeaderSeq());
		h.setTotalHour(getSumOfParticipation(h.getHandscanrecords()));
		h.setRemainingHour(subtrMinsFromMins( h.getWorkingHourInMin(), h.getTotalHour()));			
		handscandao.saveHandscanHeader(h);
	}

	@Override
	public HandScanRecord getMatchingHandScanRecord(HandScanHeader hd, String dateComp) {
		try {
			if(hd != null && hd.getHeaderSeq() != null){
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
	
	
	private Integer getSumOfParticipation(List<HandScanRecord> hsr){
		int hourPortion = 0;
		int minPortion = 0;		
 		if(hsr!= null && hsr.size() > 0){
			for(HandScanRecord r: hsr){
				if(r.getParticipationTime() != null){					
					hourPortion += r.getParticipationTime().getHours();
					minPortion += r.getParticipationTime().getMinutes();
				}
			}			
			hourPortion = hourPortion * 60;
			return minPortion + hourPortion ;	
 		}
 		return null;
	}
	
	private long getSumOfParticipationNotUsed(List<HandScanRecord> hsr){
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
	
	public HandScanHeader getHandScanHeaderById(Integer id){
		return handscandao.getHandScanHeaderById(id);
	}
}

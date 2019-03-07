package com.upa.web.restcontroller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upa.service.HandScanService;
import com.upa.service.UserService;
import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.HandScanHeader;
import com.upa.web.model.entity.HandScanRecord;;



@RestController
public class TimeClockController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private HandScanService handscanservice;
	private UserService userservice;

	
	@Autowired(required=true)
	@Qualifier(value="handScanService")
	public void HandScanService(HandScanService ps){
		this.handscanservice = ps;
	}

	@Autowired(required=true)
	@Qualifier(value="userservice")
	public void UserService(UserService us){
		this.userservice = us;
	}


	@RequestMapping(value = "/timeclockws", method = RequestMethod.GET,headers="Accept=application/json")
	public HandScanHeader getHandScanHeader()
	{
		//http://localhost:8080/mitta-webapp/timeclockws
		AppUser appuser = new AppUser();
		/*appuser.setUserId("KIWASAKI");
		appuser.setUserPassword("welcome1");*/
	
		UserValidationResult rs = this.userservice.isValidUserPassword(appuser);
		appuser = rs.getAppuser();

		
		HandScanHeader handscanheader = this.handscanservice.getHandScanOfTerm(getCurrentTime(), appuser.getUserId());				
		return handscanheader;
	}
	
	
	@RequestMapping(value = "/gethsrec", method = RequestMethod.GET,headers="Accept=application/json")
	public List<HandScanRecord> getHandScanRecords()
	{
		//http://localhost:8080/mitta-webapp/gethsrec
		AppUser appuser = new AppUser();
		/*appuser.setUserId("KIWASAKI");
		appuser.setUserPassword("welcome1");*/
	
		UserValidationResult rs = this.userservice.isValidUserPassword(appuser);
		appuser = rs.getAppuser();

		
		HandScanHeader handscanheader = this.handscanservice.getHandScanOfTerm(getCurrentTime(), appuser.getUserId());				
 		return handscanheader.getHandscanrecords();
	}
	
	
	private Date getCurrentTime(){
		Date date = new Date();
		return date;
	}
	
}

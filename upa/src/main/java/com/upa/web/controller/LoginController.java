package com.upa.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.upa.web.beans.MyBean;
import com.upa.web.model.AppUser;
import com.upa.web.service.UserService;

import Login.Login;

@Controller
public class LoginController {
	
	private AppUser appuser = new AppUser();
	
	private UserService userservice;
	
	private MyBean myBean;

	@Autowired
	public void setMyBean(MyBean myBean) {
		this.myBean = myBean;
	}
	
	@Autowired(required=true)
	@Qualifier(value="userservice")
	public void UserService(UserService us){
		this.userservice = us;
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getdata() {
		
		List<String> list = getList();

	    //This is where the default page is set. Depending on the purpose, change the default jsp.	
		//ModelAndView model = new ModelAndView("index");
		ModelAndView model = new ModelAndView("login");
		
		AppUser appuser = new AppUser();		
		
		model.addObject("appuserEntity", appuser);
		

		model.addObject("lists", list);
		
		return model;
		/*
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		
		myBean.setName("OMAME");
		model.addObject("myBean", myBean);
		model.addObject("errorMsg", null);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		model.addObject("currentDateTime", dateFormat.format(date).toString());
		*/
	}
	

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute Login login){
		ModelAndView mv = new ModelAndView("index");
		System.out.println("submit the login user");
		
		System.out.println("User ID "+ login.getUserId());
		System.out.println("PASSWORD " + login.getPassword());
		
		boolean isValid = this.userservice.isValidUserPassword(login);
		
		if(isValid == true){
			return mv;
		}else{
			return null;
		}
	}

	private List<String> getList() {

		List<String> list = new ArrayList<String>();
		list.add("List A");
		list.add("List B");
		list.add("List C");
		list.add("List D");
		list.add("List 1");
		list.add("List 2");
		list.add("List 3");

		return list;

	}

	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

}
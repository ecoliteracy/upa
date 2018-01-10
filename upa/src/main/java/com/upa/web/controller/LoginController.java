package com.upa.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.upa.web.beans.MyBean;
import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;
import com.upa.web.service.UserService;


@Controller
//@SessionAttributes(value ="appuser", types={AppUser.class})
@SessionAttributes("appuser")
public class LoginController {
	/*
	 * Add appuser in model attribute
	 */
	@ModelAttribute("appuser")
	public AppUser setUpUserForm() {
		return new AppUser();
	}
		
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
		System.out.println("LoginController.getdata");

		
		List<String> list = getList();

	    //This is where the default page is set. Depending on the purpose, change the default jsp.	
		//ModelAndView model = new ModelAndView("index");
		ModelAndView model = new ModelAndView("login");
		
		AppUser appuser = new AppUser();		
		
		model.addObject("appuser", appuser);		

		model.addObject("lists", list);
		
		return model;
	}
	

	//@ModelAttribute("appuser")
	//@PostMapping("/loginProcess")
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(@ModelAttribute("appuser") AppUser appuser){
		
		System.out.println("LoginController.loginProcess");
		ModelAndView mv = null;
		
		/*Shortcut for development*/
		appuser.setLoginId("KIWASAKI");
		appuser.setUserPassword("welcome1");
		
		UserValidationResult rs = this.userservice.isValidUserPassword(appuser);
		String rtnrst = rs.getValidationResult();
				
		if(rtnrst.equals("VALID")){
			mv = new ModelAndView("index");
			appuser = rs.getAppuser();
			mv.addObject("appuser", appuser);
			return mv;
		}else if(rtnrst.equals("WRONG_PASSWORD")){
			mv = new ModelAndView("login");
			mv.addObject("message", "User Password is incorrect");
			return mv;
		}else if(rtnrst.equals("NOT_FOUND")){
			mv = new ModelAndView("login");
			mv.addObject("message", "User ID is not valid");
			return mv;
		}else{
			mv = new ModelAndView("login");
			mv.addObject("message", "Error.  Cannot login.");
			return mv;
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
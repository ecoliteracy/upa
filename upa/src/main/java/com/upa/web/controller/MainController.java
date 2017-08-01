package com.upa.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.upa.web.beans.MyBean;

@Controller
public class MainController {
	//MainController is the first java file being executed at tomcat server.
	
	private MyBean myBean;

	@Autowired
	public void setMyBean(MyBean myBean) {
		this.myBean = myBean;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getdata() {
		
		List<String> list = getList();

	    //This is where the default page is set. Depending on the purpose, change the default jsp.	
		//ModelAndView model = new ModelAndView("index");
		ModelAndView model = new ModelAndView("login");
		model.addObject("lists", list);
		
		/*
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		*/
		
		myBean.setName("OMAME");
		model.addObject("myBean", myBean);
		model.addObject("errorMsg", null);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		model.addObject("currentDateTime", dateFormat.format(date).toString());
		
		return model;
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

}
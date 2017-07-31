package com.upa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller 
public class ThirdPageController {

	@RequestMapping("/third")
	public ModelAndView getData(){

		ModelAndView model  = new ModelAndView("third/thirdpage");

		return model;
	}
}


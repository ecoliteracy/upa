package com.upa.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.upa.web.model.TimeClocker;
import com.upa.web.model.entity.AppUser;

@Controller
public class AppUserController {
	private Logger logger = LoggerFactory.getLogger(getClass());


	@RequestMapping(value="/profiles/appuser/salarytype")
	public ModelAndView getData(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute TimeClocker hs){
		logger.trace("AppUserController - getData");
		return null;
	}
	
	
	@RequestMapping("/submitUserType")
	public ModelAndView submit(@SessionAttribute("appuser") AppUser appuser, @ModelAttribute TimeClocker hs){
		logger.trace("AppUserController - submit");
		return null;
	}
}

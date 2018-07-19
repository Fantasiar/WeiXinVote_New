package com.neuedu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.model.po.Activity;
import com.neuedu.model.po.Candidate;
import com.neuedu.model.service.VoteService;

@Controller
public class HomeController {
	
	@Autowired
	private VoteService voteService;
	
	@RequestMapping(value="/activity/{aid}", method=RequestMethod.GET)
	@ResponseBody
	public Activity getActivityById(@PathVariable int aid)
	{
		
		return voteService.selectActivityById(aid);
	}
	
	@RequestMapping(value="/candidates/{aid}/{pagenum}", method=RequestMethod.GET)
	@ResponseBody
	public List<Candidate> getCandiates(@PathVariable int aid, @PathVariable int pagenum)
	{
		
		return voteService.selectCandiates(aid, pagenum, 10);
	}
	
	@RequestMapping(value="/candidates/{name}", method=RequestMethod.POST)
	@ResponseBody
	public List<Candidate> getCandiates(@PathVariable String name)
	{
		System.out.println(name);
		List<Candidate> list = new ArrayList<Candidate>();
		Candidate c1 = new Candidate();
		c1.setCid(1);
		c1.setAid(1);
		c1.setCname("Ð¡ÃÀ");
		c1.setImgurl("1.png");
		c1.setTickets(0);
		list.add(c1);
		Candidate c2 = new Candidate();
		c2.setCid(2);
		c2.setAid(1);
		c2.setCname("Ð¡Àö");
		c2.setImgurl("2.png");
		c2.setTickets(0);
		list.add(c2);
		return list;
		//return voteService.selectCandiateByName(name);
	}

	
}

package com.neuedu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.model.po.Candidate;
import com.neuedu.model.service.VoteService;

@Controller
public class DetailsController {
	
	@Autowired
	private VoteService voteService;
	
	@RequestMapping(value="/candidate/{cid}", method=RequestMethod.GET)
	@ResponseBody
	public Candidate getCandidateById(@PathVariable int cid)
	{
		
		Candidate c = voteService.getCandidateById(cid);
		
		return c;
	}
	
	
}

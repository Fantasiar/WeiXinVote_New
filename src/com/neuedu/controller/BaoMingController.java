package com.neuedu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.model.po.Candidate;
import com.neuedu.model.service.VoteService;

@Controller
public class BaoMingController {
	@Autowired
	private VoteService voteService;
	
	@RequestMapping(value="/candidates", method=RequestMethod.POST)
	@ResponseBody
	public String baoming(Candidate c, @RequestParam MultipartFile[] upload, HttpServletRequest request)
	{	
		/*c.setImgurl("4.jpg");*/
		String uploadpath = request.getServletContext().getRealPath("/img");
		voteService.saveCandidate(c,upload,uploadpath);
		return "{\"result\":true}";
	}
	
}

package com.neuedu.model.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.model.mapper.ActivityMapper;
import com.neuedu.model.mapper.CandidateMapper;
import com.neuedu.model.mapper.ImagesMapper;
import com.neuedu.model.po.Activity;
import com.neuedu.model.po.Candidate;
import com.neuedu.model.po.Images;
import com.neuedu.model.po.LW;
import com.neuedu.model.po.LWRecord;
import com.neuedu.model.po.Prize;
import com.neuedu.model.po.Voterecord;

@Service
public class VoteService {
	
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private CandidateMapper candidateMapper;
	@Autowired
	private ImagesMapper imagesMapper;
	
	public Activity selectActivityById(int aid){
		return activityMapper.getActivityById(aid);
	}
	
	public List<Candidate> selectCandiates(int aid,int pagenum,int pageSize){
		return candidateMapper.getCandidates(aid, pagenum, pageSize);
	}
	
	public void saveCandidate(Candidate c, MultipartFile[] upload, String uploadpath){
		//文件上传
		for(int i=0; i<upload.length;i++)
		{
			String filename = System.currentTimeMillis()+upload[i].getOriginalFilename();
			File f = new File(uploadpath,filename);
			try {
				upload[i].transferTo(f);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==0)
			{
				c.setImgurl(filename);
				//2. addcandidate
				candidateMapper.saveCandidate(c);
			}
			
			//保存图片
			Images image = new Images();
			image.setCid(c.getCid());
			image.setImgurl(filename);
			imagesMapper.addImage(image);
		}
		//更改报名人数
		activityMapper.updateActivityPeople(c.getAid());		
	}
	
	public Candidate getCandidateById(int cid)
	{
		return candidateMapper.getCandidateById(cid);
	}
}

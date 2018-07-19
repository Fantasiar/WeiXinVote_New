package com.neuedu.model.mapper;

import com.neuedu.model.po.Activity;

public interface ActivityMapper {
	
	public Activity getActivityById(int aid);
	
	public void updateActivityPeople(int aid);
}

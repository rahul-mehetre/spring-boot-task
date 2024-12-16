package com.example.demo.service.impl;

import com.example.demo.beans.CommonResponseBean;
import com.example.demo.beans.UserBean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServiceIMPL implements UserService {

	@Autowired
	private UserRepo userRepo;


	@Override
	public CommonResponseBean saveUser(UserBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		User user = mapBeanToEntity(bean);
		if(user !=null){
			User saveUser = userRepo.save(user);
			responseBean.setStatus(true);
			responseBean.setMessage("User Saved Successfully.");
			responseBean.setResponseBean(saveUser);
		}else{
			responseBean.setStatus(false);
			responseBean.setMessage("Error : Unable To Validate Data.");
		}


		return responseBean;
	}

	@Override
	public CommonResponseBean getUser(Long id) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Optional<User> saveUser = userRepo.findById(id);
		if(saveUser.isPresent()) {
			responseBean.setStatus(true);
			responseBean.setMessage("User Details.");
			responseBean.setResponseBean(saveUser.get());
		}else {
			responseBean.setStatus(false);
			responseBean.setMessage("User Not Found");
			responseBean.setResponseBean(null);
		}
	
		return responseBean;
	}

	@Override
	public boolean deleteUser(Long id) {
		return false;
	}

	@Override
	public User mapBeanToEntity(UserBean bean) {
		User u = new User();
		if(bean.getUserId() != null) u.setUserId(bean.getUserId());

		if(bean.getUserAge() != null) u.setAge(bean.getUserAge());

		if(bean.getUserName() != null) u.setName(bean.getUserName());

		return u;
	}

	@Override
	public UserBean mapEntityToBean(User user) {
		return null;
	}

	@Override
	public CommonResponseBean getUserNotificationCount(Long userId) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Long count = userRepo.getNotificationCountForUser(userId);
		if(count>0) {
			responseBean.setStatus(true);
			responseBean.setMessage("User Notification Count is : "+count);
			responseBean.setResponseBean(count);
		}else {
			responseBean.setStatus(false);
			responseBean.setMessage("User Notificaton Not Found");
			responseBean.setResponseBean(null);
		}
	
		return responseBean;
	}


}

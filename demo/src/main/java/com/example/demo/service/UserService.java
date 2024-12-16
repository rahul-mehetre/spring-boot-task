package com.example.demo.service;

import com.example.demo.beans.CommonResponseBean;
import com.example.demo.beans.UserBean;
import com.example.demo.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public CommonResponseBean saveUser(UserBean bean);
	public CommonResponseBean getUser(Long id);
	public boolean deleteUser(Long id);

	public User mapBeanToEntity(UserBean beans);
	public UserBean mapEntityToBean(User user);

	
	public CommonResponseBean getUserNotificationCount(Long userId);
}

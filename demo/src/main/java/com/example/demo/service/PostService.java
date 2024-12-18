package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.beans.CommonResponseBean;
import com.example.demo.beans.UserPostBean;
import com.example.demo.entities.UserPost;

@Service
public interface PostService {
	
	public CommonResponseBean savePost(UserPostBean bean);
	public UserPostBean getPost(Long id);
	public boolean deletePost(Long id);

	public CommonResponseBean insertLike(UserPostBean bean,Long postId);
	
	public UserPost mapBeanToEntity(UserPostBean beans,CommonResponseBean responseBean);
	public UserPostBean mapEntityToBean(UserPost user,CommonResponseBean responseBean);
	
}

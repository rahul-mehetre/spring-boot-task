package com.example.demo.service.impl;

import java.beans.Beans;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.CommonResponseBean;
import com.example.demo.beans.UserPostBean;
import com.example.demo.entities.Notification;
import com.example.demo.entities.User;
import com.example.demo.entities.UserPost;
import com.example.demo.repo.NotificationRepo;
import com.example.demo.repo.PostRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.PostService;

@Service
public class PostServiceIMPL implements PostService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;


	@Override
	public CommonResponseBean savePost(UserPostBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		UserPost post = mapBeanToEntity(bean, responseBean);
		if (post != null) {
			Optional<User> userOptional = userRepo.findById(bean.getUserId());
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				user.getPosts().add(post);
				User result = userRepo.save(user);
				if (result != null) {
					responseBean.setStatus(true);
					responseBean.setMessage("Post Added Successfully.");
					responseBean.setResponseBean(result);
				}
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("User Not Found !");
				responseBean.setResponseBean(null);
			}

		} else {
			responseBean.setStatus(false);
			responseBean.setMessage("Error : Unable To Validate Data.");
		}
		return responseBean;
	}

	@Override
	public UserPostBean getPost(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePost(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserPost mapBeanToEntity(UserPostBean beans, CommonResponseBean responseBean) {
		UserPost post = new UserPost();

		if (beans.getLike() != null)
			post.setLikesCount(beans.getLike());
		else
			post.setLikesCount(0L);
		if (beans.getPostId() != null)
			post.setPostId(beans.getPostId());
		if (beans.getContent() != null)
			post.setContent(beans.getContent());

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String timestamp = currentDateTime.format(formatter);
		post.setTime(timestamp);
		return post;
	}

	@Override
	public CommonResponseBean insertLike(UserPostBean bean, Long userId, Long postId) {
		CommonResponseBean responseBean = new CommonResponseBean();
		if (bean.getLike() != null) {
			Optional<UserPost> userpost1 = postRepo.findById(postId);
			if (userpost1.isPresent()) {
				UserPost userpost = userpost1.get();
				Long likeCount = userpost.getLikesCount() + bean.getLike();
				userpost.setLikesCount(likeCount);

				Notification notify = new Notification();

				LocalDateTime currentDateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String timestamp = currentDateTime.format(formatter);

				notify.setDate(timestamp);

				userpost.getNotifications().add(notify);

				UserPost result = postRepo.save(userpost);

				responseBean.setStatus(true);
				responseBean.setMessage("Like Added Successfully.");
				responseBean.setResponseBean(result);

			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("System Error : Unable To Add Like .");
			}
		} else {
			responseBean.setStatus(false);
			responseBean.setMessage(" Error : Like Count is not present  .");
		}

		return responseBean;
	}

	@Override
	public UserPostBean mapEntityToBean(UserPost user, CommonResponseBean responseBean) {
		// TODO Auto-generated method stub
		return null;
	}

}

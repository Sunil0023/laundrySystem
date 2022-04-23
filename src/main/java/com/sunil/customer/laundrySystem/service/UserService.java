package com.sunil.customer.laundrySystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.customer.laundrySystem.model.User;
import com.sunil.customer.laundrySystem.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; 
	
	public List<User> getAllUser() {
		List<User> users=userRepository.findAll();
		return users;
	}
		
	
	public User saveUser(User user) {
		User savedUser=userRepository.save(user);
		return savedUser;
	}
	
	
	public User getUser(String userName) {
		User user=userRepository.findByUsername(userName);
		return user;
	}
	
	

}

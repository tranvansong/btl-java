package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public List<User> getListCustomers() {
		List<User> customers = new ArrayList<>();
		for (User user : this.findAll()) {
			if(user.getRole().equals("USER")) customers.add(user);
		}
		return customers;
	}

	@Override
	public List<User> getListAdmins() {
		List<User> admins = new ArrayList<>();
		for (User user : this.findAll()) {
			if(user.getRole().equals("ADMIN")) admins.add(user);
		}
		return admins;
	}

	@Override
	public void saveAdmin(User user) {
		userRepository.save(user);
	}

	@Override
	public void saveUser(User user) {
		boolean kt = true;
		user.setRole("USER");
		String email = user.getEmail();
		System.out.println(email);
		for (User u : this.findAll()) {
			if(u.getEmail().equals(email)) {
				kt=false;
				break;
			}
		}
		if(kt) userRepository.save(user);
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public boolean isDuplicatedEmail(String email) {
		for (User user : this.getListAdmins()) {
			if(user.getEmail().toLowerCase().equals(email.toLowerCase())) return true;
		}
		return false;
	}

	@Override
	public List<User> findByKeyword(String keyword) {
		return userRepository.findByKeyword(keyword);
	}
}

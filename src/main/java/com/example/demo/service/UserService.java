package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
	public List<User> findAll();
	public List<User> getListCustomers();
	public List<User> getListAdmins();
	public User findUserById(int id);
	public void saveAdmin(User user);
	public void saveUser(User user);
	public void deleteUserById(int id);
	public boolean isDuplicatedEmail(String email);
	public List<User> findByKeyword(String keyword);
}

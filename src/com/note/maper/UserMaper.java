package com.note.maper;

import java.util.List;

import com.note.model.User;

public interface UserMaper {
	public void insertUser(User user);
	public void deleteUserById(int id);
	public void updateUser(User user);
	public User getUserById(int id);
	public List<User> getUserAll();
}

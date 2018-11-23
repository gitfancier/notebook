package com.note.maper;

import com.note.model.User;

public interface LoginMaper {
	public void updateUser(User user);
	public String getPasswordByUsername(String username);
}

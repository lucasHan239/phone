package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User checkLogin(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		return user;
	}
	
	public boolean checkUsername(String username) {
		if (userRepository.findByUsername(username) == null) {
			return true;
		}
		return false;
	}
	
	public void insertUser(User user) {
		userRepository.insertUser(user.getUsername(), user.getPassword());
	}
	
	
	public String getUserFB() {
        FacebookClient facebookClient = new DefaultFacebookClient("262709531687341", "4f4cee2c002f068b32617e5def6ea893", Version.LATEST);
        com.restfb.types.User user = facebookClient.fetchObject("me", com.restfb.types.User.class,
                Parameter.with("fields", "name"));
        System.out.println(user.getName());
        return user.getName();
    }
}

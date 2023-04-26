package com.leeneko.user.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leeneko.user.Service.GetUser;
import com.leeneko.user.Service.GetUserList;
import com.leeneko.user.model.User;
import com.leeneko.user.model.UserIn;

@RestController
public class UserController {
	
	@Getmapping("/user/test")
	public String getTest() throws Exception {
		return "test";
	}
	
	@GetMapping("/user/getList")
	public List<User> getList(UserIn input) throws Exception {
		return new GetUserList().getList(input);
	}
	
	@GetMapping("/user/get")
	public User getUser(UserIn input) throws Exception {
		return new GetUser().getUser(input);
	}
	
}

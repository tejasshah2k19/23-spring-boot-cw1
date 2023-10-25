package com.controller;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bean.UserBean;
import com.dao.UserDao;

@Controller
public class UserController {

	@Autowired
	UserDao userDao;

	// create a method that is responsible to open signup jsp file
	// in controller class method name we can take as per feature
	// like for login -> login() , for signup -> signup()
	// in controller your method must return String
	// in controller your method must mark as public

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new UserBean());// blank
		System.out.println("Signup()");
		return "Signup"; // in the return we need pass jsp name that we want to open
	}

	@GetMapping("/login")
	public String login() {
		System.out.println("login()");
		return "Login";
	}

	// @Valid will check validation inside bean
	// if error found it return error and error flag to BindingResult
	// using BindingResult we can get idea we have validation error or not ?

	@PostMapping("/saveuser")
	public String saveUser(@jakarta.validation.Valid @ModelAttribute("user") UserBean user, BindingResult result,
			Model model) {
		System.out.println(result.hasErrors());
		model.addAttribute("user", user);// set the name and value to send data to jsp

		if (result.hasErrors()) {
			return "Signup";// error found re open Signup.jsp
		} else {
			// db insert
			userDao.addUser(user);
			System.out.println(user.getFirstName());
			System.out.println(user.getEmail());
//    	model.addAttribute("firstName",user.getFirstName());
			return "Home";
		}
	}

	@GetMapping("/listusers")
	public String listUsers(Model model) {
		List<UserBean> users = userDao.getAllUsers();
		model.addAttribute("users",users);
		return "ListUser";
	}

	@GetMapping("/listusers2")
	public String listUsers2(Model model) {
		List<UserBean> users = userDao.getAllUsers2();
		model.addAttribute("users",users);
		return "ListUser";
	}
	
	
	//query string read -> @RequestParam 
	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam("userId") Integer userId) {
	
		//soft delete 
		userDao.deleteUserSoft(userId);
		return "redirect:/listusers";//redirecto listusers -> this url not a jsp 
	}

	@GetMapping("/deleteuser2")
	public String deleteUser2(@RequestParam("userId") Integer userId) {
	
		//soft delete 
		userDao.deleteUser(userId);
		return "redirect:/listusers";//redirecto listusers -> this url not a jsp 
	}
	
	//below url/method will open jsp 
	@GetMapping("/search")
	public String search() {
		return "Search";
	}

	@PostMapping("/searchuser")
	public String searchUser(@RequestParam("firstName") String firstName ,Model model) {
	List<UserBean> users =	userDao.searchUserByFirstName(firstName);
	model.addAttribute("users",users);
		return "SearchList";
	}
	
	@GetMapping("/newprofile")
	public String newProfile() {
		
		return "NewProfile";
	}

	@PostMapping("/saveprofile")
	public String saveProfile(@RequestParam("profile") MultipartFile file) {
		System.out.println(file.getOriginalFilename());// print name of the file

		try {
		String filePath = "E:\\Tejas Shah\\boot\\spring-web-cw1\\src\\main\\webapp\\profile";
		File f2 = new File(filePath,file.getOriginalFilename());
		
		byte[] b = file.getBytes();
		FileUtils.writeByteArrayToFile(f2, b);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "NewProfile";
	}

}



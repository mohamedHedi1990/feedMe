package com.biservices.wakalni.wakalni.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biservices.wakalni.wakalni.exception.FileStorageException;
import com.biservices.wakalni.wakalni.persistence.entities.user.PlatformUser;
import com.biservices.wakalni.wakalni.services.CustomUserDetailsService;
import com.biservices.wakalni.wakalni.services.DBFileStorageService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private DBFileStorageService DBFileStorageService;

	@CrossOrigin
	@GetMapping("login/{userName}/{password}")
	public PlatformUser getReport(@PathVariable("userName") String userName,
			@PathVariable("password") String password) {

		PlatformUser user = (PlatformUser) userService.loadUserByUsernameAndPassword(userName, password);
		String loginPasswordString = user.getUsername() + ":" + user.getPassword();
		String encodingString = Base64.getEncoder().encodeToString(loginPasswordString.getBytes());
		System.out.println("encodingString " + encodingString);
		user.setAuthorization(encodingString);
		return user;
	}

	@CrossOrigin
	@PostMapping()
	public PlatformUser saveNewUser(@RequestBody PlatformUser user, @RequestParam("file") MultipartFile file) {
		try {
			PlatformUser savedUser = userService.saveNewUser(user);
			String fileName = DBFileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/wakalni/"+savedUser.getId()+"/")
						.path(fileName).toUriString();
			savedUser.setPictureUrl(fileDownloadUri);
			return userService.saveNewUser(savedUser);
		} catch (FileStorageException e) {
			System.out.println("Internal error was occured when trying to save user picture file! \n");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Internal error was occured when trying to save the informations of this new user \n");
			e.printStackTrace();
		}
		return null;
	}
}

package com.sunil.customer.laundrySystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.customer.laundrySystem.model.User;
import com.sunil.customer.laundrySystem.model.Users;
import com.sunil.customer.laundrySystem.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path="/getUsers",produces = "application/json")
	public Users getAllUser() {
		System.out.println("Inside Get Users");
		Users usersList=new Users();

		List<User> users=userService.getAllUser();
		usersList.setUsersList(users);
		return usersList;
	}

	@GetMapping("/downloadUsersFile")
	public ResponseEntity<Object> getDownloadedFile() throws IOException {
		Users usersList=new Users();

		List<User> users=userService.getAllUser();
		FileWriter filewriter =  null;

		try {
			System.out.println("Users: "+users.toString());

			StringBuilder filecontent = new StringBuilder("UserId, UserName, Email_Id\n");

			for(User usr:users) {
				filecontent.append(usr.getUserId()).append(",").append(usr.getUsername()).append(",").append(usr.getEmailId()).append("\n");
			}

			String filename = "C:\\Sunil Data\\Personal\\Reading\\lmsclient_data\\source\\UsresFileSrcData.csv";

			filewriter=new FileWriter(filename);
			filewriter.write(filecontent.toString());
			filewriter.flush();

			File file=new File(filename);
			System.out.println("FileName: "+file.getName());

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");

			ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);

			System.out.println("responseEntity on Server: "+responseEntity);

			return responseEntity;
		} catch (Exception e ) {
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
		} finally {
			if(filewriter!=null)
				filewriter.close();
		}
	}



	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		System.out.println("Inside saveUser");
		User savedUsr=userService.saveUser(user);
		return savedUsr;
	}
	

	@GetMapping("/getUser/{userName}")
	public User getUser(@PathVariable String userName) {
		User user=userService.getUser(userName);
		return user;
	}
	
//	@RequestMapping(value="/source",method=RequestMethod.GET)
	@GetMapping("/source")
	public String sourcePath(ModelMap model) {
		return "source";
	}
}

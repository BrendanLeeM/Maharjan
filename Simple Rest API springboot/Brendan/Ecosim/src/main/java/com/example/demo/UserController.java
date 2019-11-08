package com.example.demo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class that handles log in and create account attempts from client
 * @author Brendan
 *
 */
@RestController
class UserController {

    @Autowired
    userRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Method to create a new user using an HTTP Post request
     * @param user
     * @return user
     */
     @RequestMapping(method = RequestMethod.POST, path = "/user/new")
     public User saveUser(@RequestBody User user) {
    	 
    	 String verify = "Success";
    	 
    	 if(user.getUserName() == null) {
    		 user.setUsername("Default");
    	 }
    	 
    	 if(user.getpassword() == null) {
    		 user.setpassword("Default");
    	 }
    	 
    	 if(user.getUserType() == null) {
    		 user.setUserType("Default");
    	 }
    	 
    	 List<User> users = userRepository.findAll();
    	 
    	 for(int i = 0; i < users.size();i++) {
    		if(users.get(i).getUserName().equalsIgnoreCase(user.getUserName())) {
    			verify = "Failed";
    		}
    	 }
    	 
    	 if (verify == "Success") {
    		 userRepository.save(user);
    		 user.setUsername("Success");
    	 } else {
    		 user.setUsername("Failed");
    	 }
    	 
        return user;
    }
     /**
      * Method to login using HTTP Post request
      * @param user
      * @return user
      */
     @RequestMapping(method = RequestMethod.POST, path = "/user")
     public User validateUser(@RequestBody User user) {
    	 
    	 String verify = "Failed";
    	 
    	 if(user.getUserName() == null) {
    		 user.setUsername("Default");
    	 }
    	 
    	 if(user.getpassword() == null) {
    		 user.setpassword("Default");
    	 }
    	 
    	 if(user.getUserType() == null) {
    		 user.setUserType("Default");
    	 }
    	 
    	 List<User> users = userRepository.findAll();
    	 
    	 for(int i = 0; i < users.size();i++) {
    		if(users.get(i).getUserName().equalsIgnoreCase(user.getUserName())) {
    			if(users.get(i).getpassword().equalsIgnoreCase(user.getpassword())) {
    				verify = "Success";
    			}
    		}
    	 }
    	 
    	 if (verify == "Success") {
    		 user.setUsername("Success");
    	 } else {
    		 user.setUsername("Failed");
    	 }
    	 
        return user;
    }
     
     @RequestMapping(method = RequestMethod.POST, path = "/user/delete")
     public void deleteUser(@RequestBody User user) throws IOException {
    	 
    	 String userName = user.getUserName();
    	 List<User> userList = userRepository.findAll();
    	 for(int i = 0; i < userList.size();i++) {
    		 if (userList.get(i).getUserName().equals(userName)) {
    			 userRepository.delete(userList.get(i));
    			 break;
    		 }
    	 }
    	 
     }

}

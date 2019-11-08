package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Simple service class which implements methods to get users from the repository through different 
 * attributes
 * @author Brendan
 *
 */
@Service
public class UserService {

	@Autowired
	private static userRepository repo;
/**
 * Pull a user from the database by ID number
 * @param id
 * @return user
 */
	
	public User findByID(int id) {
		return repo.findByID(id);

	}
	/**
	 * Pull a user by username
	 * @param username
	 * @return user
	 */

	public User findByUserName(String userName) {
		return repo.findByUserName(userName);

	}


}


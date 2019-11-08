package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Springboot repositry class for log in table
 * @author Brendan
 *
 */
@Repository
public interface userRepository extends JpaRepository<User, String> {
	
	User save(User user);
	
	public default ArrayList<User> makeListFromID(ArrayList<Integer> list, ArrayList<User> ulist){
		ArrayList<User> userList = new ArrayList<User>();
		for(int i = 0; i<list.size(); i++) {
			for (int j = 0; j<ulist.size(); j++) {
				if(ulist.get(j).getId() == list.get(i))
					userList.add(ulist.get(j));
			}
		}
		return userList;
	}
	
	public default ArrayList<User> getListofUserType(String userType, ArrayList<User> ulist){
		ArrayList<User> userList = new ArrayList<User>();
		for (int j = 0; j<ulist.size(); j++) {
			if(ulist.get(j).getUserType().equals(userType))
				userList.add(ulist.get(j));
		}
		return userList;
	}

	void delete(User user);

	User findByID(int ID);

	User findByUserName(String username);

}

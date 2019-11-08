package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



public class EcosimApplicationTests {

	@InjectMocks
	EcosimApplication app = new EcosimApplication();

	UserService userService;

	@Mock
	User mockUser;
	
	@Mock
	userRepository repo;
	
	@Mock
	worldRepository wrepo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	


	@Test
	public void getUserByIdTest() {

		when(repo.findByID(1)).thenReturn(new User(1,"Mock","Test","Admin"));

		User user = repo.findByID(1);


		assertEquals("1", user.getId().toString());
		assertEquals("Test", user.getpassword());
		assertEquals("Mock", user.getUserName());
	}


	@Test
	public void findUserByNameTest() {
		when(repo.findByUserName("Name")).thenReturn(new User(1,"Name","Test","Admin"));

		User user = repo.findByUserName("Name");


		assertEquals("1", user.getId().toString());
		assertEquals("Test", user.getpassword());
		assertEquals("Name", user.getUserName());

	}
	
	@Test
	public void makeListfromIDTest() {
		User user1 = new User(1, "user1", "123", "user");
		User user2 = new User(2, "user1", "456", "user");
		User user3 = new User(3, "user3", "789", "user");
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user3);
		userList.add(user2);
		userList.add(user1);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		
		when(repo.makeListFromID(list, userList)).thenCallRealMethod();

		ArrayList<User> ulist = repo.makeListFromID(list, userList);
		

		assertEquals(2, ulist.size());


	}
	
	@Test
	public void makeListfromUserTypeTest() {
		User user1 = new User(1, "user1", "123", "Admin");
		User user2 = new User(2, "user1", "456", "User");
		User user3 = new User(3, "user3", "789", "User");
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user3);
		userList.add(user2);
		userList.add(user1);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		
		when(repo.getListofUserType("Admin", userList)).thenCallRealMethod();

		ArrayList<User> ulist = repo.getListofUserType("Admin", userList);
		

		assertEquals(1, ulist.size());
		assertEquals("user1", ulist.get(0).getUserName());


	}

	@Test

	public void getWorldByIdTest() {
		when(wrepo.findByID(1)).thenReturn(new Worlds(1,"Mock","goats, wolves", "trees","owner"));
		
		Worlds world = wrepo.findByID(1);

		assertEquals("1", world.getId().toString());
		assertEquals("trees", world.getResources());
		assertEquals("Mock", world.getWname());
	}
	
	@Test

	public void getWorldByNameTest() {
		when(wrepo.findBywName("worldname")).thenReturn(new Worlds(1,"worldname","goats, wolves", "trees","owner"));
		
		Worlds world = wrepo.findBywName("worldname");


		assertEquals("1", world.getId().toString());
		assertEquals("goats, wolves", world.getSpecies());
		assertEquals("worldname", world.getWname());
	}
	
}

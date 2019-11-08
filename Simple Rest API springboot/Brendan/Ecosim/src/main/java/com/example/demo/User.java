package com.example.demo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

/**
 * User entity class which contains the data for a user and constructors 
 * @author Brendan
 *
 */
@Entity
@Table(name = "LogIn")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer iD;
    
    @Column(name = "Username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String userName;

    @Column(name = "Password")
    @NotFound(action = NotFoundAction.IGNORE)
    private String password;
    
    @Column(name = "usertype")
    @NotFound(action = NotFoundAction.IGNORE)
    private String usertype;
    
/**
 * Constructor for user
 * @param id
 * @param username
 * @param password
 */

    public User(int id, String userName, String password, String usertype) {
		this.iD = id;
		this.userName = userName;
		this.password = password;
		this.usertype = usertype;
	}
    
    public User() {
    	
    }

/**
 * getter method for username
 * @return
 */
    
	public String getUserName() {
        return this.userName;
    }
	
/**
 * setter method for username
 * @param Username
 */
	
    public void setUsername(String Username) {
        this.userName = Username;
    }
    
/**
 * getter method for password
 * @return
 */
    
    public String getpassword() {
        return this.password;
    }
    
/**
 * setter method for password
 * @param Password
 */
    
    public void setpassword(String Password) {
        this.password = Password;
    }
    
/**
 * getter method for ID
 * @return
 */
    
    public Integer getId() {
        return iD;
    }
    
/**
 * setter method for ID
 * @param id
 */
    
    public void setId(Integer id) {
        this.iD = id; 
    }
    
    public void setUserType(String usertype) {
        this.usertype = usertype; 
    }
    
    public String getUserType() {
        return this.usertype; 
    }
    
        @Override
    public String toString() {
        return new ToStringCreator(this)

        		.append("ID", this.getId())
                .append("Username", this.getUserName())
                .append("Password", this.getpassword())
                .append("usertype", this.getUserType()).toString();
    }
}

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
 * Class for world entities which contains world attributes and constructors
 * @author Brendan
 *
 */
@Entity
@Table(name = "Worlds")
public class Worlds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer iD;
	
	@Column(name = "Wname")
	@NotFound(action = NotFoundAction.IGNORE)
	private String wName;
    
    @Column(name = "Species")
    @NotFound(action = NotFoundAction.IGNORE)
    private String species;

    @Column(name = "Resources")
    @NotFound(action = NotFoundAction.IGNORE)
    private String resources;
    

    @Column(name = "Wowner")
    @NotFound(action = NotFoundAction.IGNORE)
    private String wOwner;

    /**
     * Constructor for Worlds
     * @param id
     * @param wname
     * @param resources
     * @param species
     * @param wowner
     */


        public Worlds(int id, String wname, String species, String resources, String wowner) {
    		this.iD = id;
    		this.wName = wname;
    		this.species = species;
    		this.resources = resources;
    		this.wOwner = wowner;
    	}
        
        public Worlds() {
        	
        }
/**
 * Getter for world name
 * @return String wname
 */
    public String getWname() {
        return this.wName;
    }
/**
 * setter for World name
 * @param Wname
 */
    public void setWname(String Wname) {
        this.wName = Wname;
    }
    /**
     * getter for species
     * @return String species
     */
    public String getSpecies() {
        return this.species;
    }
/**
 * setter for species
 * @param Species
 */
    public void setSpecies(String Species) {
        this.species = Species;
    }
/**
 * getter for resources
 * @return String Resources
 */
    public String getResources() {
        return this.resources;
    }
/**
 * setter for resources
 * @param Resources
 */
    public void setResources(String Resources) {
        this.resources = Resources;
    }
/**
 * getter for ID
 * @return int ID
 */
    public Integer getId() {
        return iD;
    }
/**
 * setter for ID
 * @param id
 */
    public void setId(Integer id) {
        this.iD = id; 
    }
    
    public String getWowner() {
        return this.wOwner;
    }
    
    public void setWowner(String wowner) {
    	this.wOwner = wowner;
    }
    
        @Override
    public String toString() {
        return new ToStringCreator(this)

        		.append("ID", this.getId())
                .append("Wname", this.getWname())
                .append("Species", this.getSpecies())
                .append("Resources", this.getResources())
                .append("Wowner", this.getWowner()).toString();
    }
}
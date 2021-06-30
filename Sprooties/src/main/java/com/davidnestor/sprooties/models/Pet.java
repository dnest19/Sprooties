package com.davidnestor.sprooties.models;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Pets")
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String pet_type; //kind of pet "species"
	@NotBlank
	private String name;

	private HashMap<String, Object> attributes; //for future use with games
	private String image; //location for the file
	private int experience;//current exp
	private int req_exp; //required experience to level 
	private int level;
	
	
	
	public Pet() {
	}
	
	
	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public HashMap<String, Object> getAttributes() {
		return attributes;
	}


	public void setAttributes(HashMap<String, Object> attributes) {
		this.attributes = attributes;
	}


	public String getPet_type() {
		return pet_type;
	}
	public void setPet_type(String pet_type) {
		this.pet_type = pet_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getReq_exp() {
		return req_exp;
	}
	public void setReq_exp(int req_exp) {
		this.req_exp = req_exp;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_pets", 
        joinColumns = @JoinColumn(name = "pet_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

}

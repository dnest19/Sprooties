package com.davidnestor.sprooties.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String username;
	@Email
	@NotBlank
	private String email;
	@Column
	@ElementCollection(targetClass=String.class)
	private List<String> unlocks; //bought and earned items
	@Column(columnDefinition = "integer default 0")
	private Long gold; //gold earned
	
	@Column(updatable=false)
	@DateTimeFormat(pattern= "yyy-MM-DD HH:mm:ss ")
	private Date createdAt;
	@DateTimeFormat(pattern= "yyy-MM-DD HH:mm:ss ")
	private Date updateAt;
	
	private HashMap<String, String> inventory; //currently equipped items
	
	public HashMap<String, String> getInventory() {
		return inventory;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updateAt = new Date();
	}


	public List<Item> getItems() {
		return items;
	}



	public void setItems(List<Item> items) {
		this.items = items;
	}



	public void setInventory(HashMap<String, String> inventory) {
		this.inventory = inventory;
	}



	public List<Post> getPosts() {
		return posts;
	}



	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}



	public List<Pet> getPets() {
		return pets;
	}



	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdateAt() {
		return updateAt;
	}



	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}




	public List<String> getUnlocks() {
		return unlocks;
	}



	public void setUnlocks(List<String> unlocks) {
		this.unlocks = unlocks;
	}






	public Long getGold() {
		return gold;
	}



	public void setGold(Long gold) {
		if(this.gold == null) {
			this.gold = 0L;
		}
		this.gold += gold;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public void addItem(Item item) {
		this.items.add(item);
		item.getUsers().add(this);
	}




	@Size(min=8,max=100)
	@NotBlank
	private String password;
	public String getConfirmPassword() {
		return confirmPassword;
	}



	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	@Transient
	private String confirmPassword;

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Post> posts;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_pets", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets;
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "users_items", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;
    
	public User() {
		
	}
}

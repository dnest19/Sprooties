package com.davidnestor.sprooties.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidnestor.sprooties.models.Post;
import com.davidnestor.sprooties.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository pstRepo;
	
	//findall
	public List<Post> getPosts(){
		return this.pstRepo.findAll();
	}
	
	//findallbyId
	public List<Post> findAllByUser_Id(Long id){
		return this.pstRepo.findAllByUser_IdOrderByIdDesc(id);
	}
	
	//get
	public Post getById(Long id) {
		return this.pstRepo.findById(id).orElse(null);
	}
	
	//create
	public Post create(Post post) {
		return this.pstRepo.save(post);
	}
	
	//delete
	public void delete(Long id) {
		this.pstRepo.deleteById(id);
	}
	
	//update
	public Post updatePost(Post post) {
		return this.pstRepo.save(post);
	}
}

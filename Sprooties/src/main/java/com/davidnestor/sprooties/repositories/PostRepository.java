package com.davidnestor.sprooties.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.davidnestor.sprooties.models.Post;

@Repository
public interface PostRepository  extends CrudRepository<Post,Long> {
	List<Post> findAll();
	List<Post> findAllByUser_IdOrderByIdDesc(Long id);
}

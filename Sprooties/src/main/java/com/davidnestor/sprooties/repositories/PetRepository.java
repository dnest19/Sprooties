package com.davidnestor.sprooties.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.davidnestor.sprooties.models.Pet;


@Repository
public interface PetRepository extends CrudRepository<Pet,Long>  {
	List<Pet> findAll();
}

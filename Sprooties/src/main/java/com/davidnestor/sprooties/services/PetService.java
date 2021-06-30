package com.davidnestor.sprooties.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidnestor.sprooties.models.Pet;
import com.davidnestor.sprooties.repositories.PetRepository;

@Service
public class PetService {
	@Autowired
	private PetRepository pRepo;
	
	//findall
	public List<Pet> getPets(){
		return this.pRepo.findAll();
	}
	
	//get
	public Pet getById(Long id) {
		return this.pRepo.findById(id).orElse(null);
	}
	
	//create
	public Pet create(Pet pet) {
		return this.pRepo.save(pet);
	}
	
	//delete
	public void delete(Long id) {
		this.pRepo.deleteById(id);
	}
	
	//update
	public Pet updatePet(Pet pet) {
		return this.pRepo.save(pet);
	}
}

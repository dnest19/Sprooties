package com.davidnestor.sprooties.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidnestor.sprooties.models.Item;
import com.davidnestor.sprooties.repositories.ItemRepository;


@Service
public class ItemService {
	@Autowired
	private ItemRepository iRepo;
	
	//findall
	public List<Item> getItems(){
		return this.iRepo.findAll();
	}
	
	//get
	public Item getById(Long id) {
		return this.iRepo.findById(id).orElse(null);
	}
	
	//create
	public Item create(Item Item) {
		return this.iRepo.save(Item);
	}
	
	//delete
	public void delete(Long id) {
		this.iRepo.deleteById(id);
	}
	
	//update
	public Item updateItem(Item item) {
		return this.iRepo.save(item);
	}
	
	//save
	public Item saveItem(Item item) {
		return this.iRepo.save(item);
	}
}

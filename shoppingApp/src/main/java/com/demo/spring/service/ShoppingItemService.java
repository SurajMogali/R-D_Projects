package com.demo.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.ShoppingItem;
import com.demo.spring.repository.ShoppingItemRepository;

@Service
public class ShoppingItemService {
	private final ShoppingItemRepository shoppingItemRepository;

	@Autowired
	public ShoppingItemService(ShoppingItemRepository shoppingItemRepository) {
		this.shoppingItemRepository = shoppingItemRepository;
	}

	public List<ShoppingItem> getAllItems() {
		return shoppingItemRepository.findAll();
	}

	public ShoppingItem addItem(ShoppingItem item) {
		// You can add additional logic, validation, and save the item to the repository
		return shoppingItemRepository.save(item);
	}

	public ShoppingItem updateItem(Long id, ShoppingItem updatedItem) throws NotFoundException {
		// Find the existing item by its ID
		ShoppingItem existingItem = shoppingItemRepository.findById(id).orElseThrow(() -> new NotFoundException());
		existingItem.setName(updatedItem.getName());
		existingItem.setCompleted(updatedItem.isCompleted());

		return shoppingItemRepository.save(existingItem);
	}

	public void deleteItem(Long id) throws NotFoundException {

		if (!shoppingItemRepository.existsById(id)) {
			throw new NotFoundException();
		}

		shoppingItemRepository.deleteById(id);
	}
}

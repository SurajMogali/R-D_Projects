package com.demo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.ShoppingItem;
import com.demo.spring.service.ShoppingItemService;

@RestController
@RequestMapping(path = "/api/shopping-list")

public class ShoppingListController {
	@Autowired
	private ShoppingItemService shoppingItemService;

	@GetMapping(path = "/get")
	public List<ShoppingItem> getAllItems() {
		List<ShoppingItem> items = shoppingItemService.getAllItems();
		return items;
	}

	@PostMapping(path = "/save")
	public ShoppingItem addItem(@RequestBody ShoppingItem item) {
		ShoppingItem savedItem = shoppingItemService.addItem(item);
		return savedItem;
	}

	@PutMapping(path = "/update/{id}")
	public ShoppingItem updateItem(@PathVariable Long id, @RequestBody ShoppingItem item) throws NotFoundException {
		ShoppingItem updatedItem = shoppingItemService.updateItem(id, item);
		return updatedItem;
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteItem(@PathVariable Long id) throws NotFoundException {
		shoppingItemService.deleteItem(id);
	}
}

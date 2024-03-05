
package com.demo.spring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.server.ResponseStatusException;

import com.demo.spring.entity.ShoppingItem;
import com.demo.spring.service.ShoppingItemService;

public class ShoppingListControllerTest {
	@InjectMocks
	private ShoppingListController shoppingListController;

	@Mock
	private ShoppingItemService shoppingItemService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllItems() {
		// Mock the service method to return a list of shopping items
		List<ShoppingItem> shoppingItems = new ArrayList<>();
		Mockito.when(shoppingItemService.getAllItems()).thenReturn(shoppingItems);

		List<ShoppingItem> result = shoppingListController.getAllItems();

		assertEquals(shoppingItems, result);
	}

	@Test
	public void testAddItem() {
		ShoppingItem item = new ShoppingItem();
		item.setId(1L);

		
		Mockito.when(shoppingItemService.addItem(any(ShoppingItem.class))).thenReturn(item);

		ShoppingItem result = shoppingListController.addItem(item);

		assertEquals(item, result);
	}

	@Test
	public void testUpdateItem() throws NotFoundException {
		Long itemId = 1L;
		ShoppingItem item = new ShoppingItem();
		item.setId(itemId);

		
		Mockito.when(shoppingItemService.updateItem(itemId, item)).thenReturn(item);

		ShoppingItem result = shoppingListController.updateItem(itemId, item);

		assertEquals(item, result);
	}

	

	
}

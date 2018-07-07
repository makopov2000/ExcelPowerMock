package com.powermock.test5.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ControlerMockSimpleTest {
	
	private DefaultUserService mockUserService;
	
	@Test
	public void testGetUserCount() {
		mockUserService = mock(DefaultUserService.class);
		when(mockUserService.getUserCount()).thenReturn(100L);
		UserController userController = new UserController(mockUserService);
		assertEquals(100L, userController.getUserCount().longValue());
	}

}

package com.powermock;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
//import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

//import static org.easymock.EasyMock.expect;
//import static org.junit.Assert.assertEquals;
//import static org.powermock.api.easymock.PowerMock.mockStatic;
//import static org.powermock.api.easymock.PowerMock.replayAll;
//import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(UserController.class)
public class UserControllerTest {
	
	private DefaultUserService mockUserService;
	
	@Test
	public void testGetUserCount() {
		mockUserService = mock(DefaultUserService.class);
		when(mockUserService.getUserCount()).thenReturn(100L);
		UserController userController = new UserController(mockUserService);
		assertEquals(100L, userController.getUserCount().longValue());
	}
	
	@Test
	public void testMockPrivateMethod() throws Exception {
		UserController spy = spy(new UserController());
		when(spy, method(UserController.class, "getGreetingFormat")).withNoArguments().thenReturn("Good Morning %s %s");
		User user = getNewUser();
		assertEquals("Good Morning Code Geeks", spy.getGreetingText(user));
	}
	
//	@Test
//	public void testMockStatic() throws Exception {
//		PowerMock.mockStaticPartial(UUID.class, "randomUUID");		
//		EasyMock.expect(UUID.randomUUID()).andReturn(UUID.fromString("067e6162-3b6f-4ae2-a171-2470b63dff00"));
//		PowerMock.replayAll();
//		UserController userController = new UserController();
//		Assert.assertTrue(userController.createUserId(getNewUser()).contains("067e6162-3b6f-4ae2-a171-2470b63dff00"));
//		PowerMock.verifyAll();		
//	}

	private User getNewUser() {
		User user = new User();
		user.setFirstName("Code");
		user.setSurname("Geeks");
		return user;
	}
	
}

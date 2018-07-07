package com.powermock.test2.easymock;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.powermock.api.easymock.PowerMock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

//This is required for PowerMock to work.
@RunWith(PowerMockRunner.class)
// The following class are included in @PrepareForTest because:
// - SampleUtils.class. So that PowerMock will handle static method calls.
// Notice that even though SampleUtils has private constructor PowerMock can
// still handle mocking it.
//
// - SampleServiceHelper. Because its declared as final class and EasyMock wont
// be able to mock it.
//
// - SampleService.class. So that PowerMock could intercept creation
// of SampleServiceHelper class. And could do partial mocking of
// "privateMethod" private method.
@PrepareForTest({ SampleUtils.class, SampleServiceHelper.class, SampleService.class })
public class SampleServiceTest {

	@Test
	public void testMethod1() throws Exception {
		// This will mock the SampleUtils static method calls.
		PowerMock.mockStatic(SampleUtils.class);

		// Just do plain "new" of SampleService here since we are not calling
		// any private method inthis test method that need to be partially mocked.
		SampleService sampleService = new SampleService();

		// Here we are telling what should happens when SampleUtils.staticMethod()
		// gets called. We can use EasyMock to do the expectation even though PowerMock
		// is mocking the static method call.
		EasyMock.expect(SampleUtils.staticMethod()).andReturn("mockedStaticMethod");

		// We need to have PowerMock replay SampleUtils.just like its done
		// with SampleServiceHelper.class
		// We cannot use EasyMock in this case since it does not handle Class objects.
		PowerMock.replay(SampleUtils.class);

		// ------ Do actually call the method to be tested and do necessary assertions.
		String result = sampleService.method1();

		assertEquals("method1:" + "mockedStaticMethod", result);

		// ------ Do necessary verification to make sure that all methods we expected
		// to be called are called

		// Verification of Class mocks need to go through PowerMock, just like in
		// replay.
		PowerMock.verify(SampleUtils.class);
	}

	@Test
	public void testMethod2() throws Exception {
		// Here are going to tell PowerMock to replace all calls to new
		// SampleServiceHelper
		// in SampleService with sampleServiceHelperMock. Notice that even though
		// SampleServiceHelper is final
		// we now can mock it using EasyMock because is included in @PrepareForTest
		// declaration.
		// Powermock example says to use PowerMock.mock but this works as well.
		SampleServiceHelper sampleServiceHelperMock = EasyMock.createMock(SampleServiceHelper.class);
		// Here we tell PowerMock to intercept all creations of SampleServiceHelper
		PowerMock.expectNew(SampleServiceHelper.class).andReturn(sampleServiceHelperMock);
		// PowerMock requires us to call this. Otherwise it will fail to intercept call
		// to
		// "new" SampleServiceHelper. Cant use EasyMock here since it does not support
		// mocking Class objects.
		PowerMock.replay(SampleServiceHelper.class);

		// We need to create SampleService after the above call because
		// SampleServiceHelper is created during SampleService initialization.
		// "method3" will show you that you dont need to do this when object to be
		// mocked
		// is created inside method that is tested.
		//
		// Just do plain "new" of SampleService here since we are not calling any method
		// in
		// this test method that need to be partially mocked.
		SampleService sampleService = new SampleService();

		// We can use EasyMock to replay plain mocks.
		EasyMock.expect(sampleServiceHelperMock.helperMethod()).andReturn("mockedHelperMethod");

		// Again since sampleServiceHelper is mocked with EasyMock, we can replay it
		// with
		// EasyMock.
		EasyMock.replay(sampleServiceHelperMock);

		// ------ Do actually call the method to be tested and do necessary assertions.
		String result = sampleService.method2();

		assertEquals("method2:" + "mockedHelperMethod", result);

		// ------ Do necessary verification to make sure that all methods we expected
		// to be called are called

		// Verification of Class mocks need to go through PowerMock, just like in
		// replay.
		PowerMock.verify(SampleServiceHelper.class);

		EasyMock.verify(sampleServiceHelperMock);
	}

	@Test
	public void testMethod3() throws Exception {
		// Here are going to tell PowerMock to replace call to new SampleServiceHelper2
		// in SampleService with sampleServiceHelper2Mock.
		SampleServiceHelper2 sampleServiceHelper2Mock = EasyMock.createMock(SampleServiceHelper2.class);
		// Here we tell PowerMock to intercept all creations of SampleServiceHelper2
		PowerMock.expectNew(SampleServiceHelper2.class).andReturn(sampleServiceHelper2Mock);

		// Here we dont need to create SampleService before calling
		// PowerMock.replay(SampleServiceHelper2.class), since SampleServiceHelper2
		// is created inside method3.
		//
		// Just do plain "new" of SampleService here since we are not calling any method
		// in
		// this test method that need to be partially mocked.
		SampleService sampleService = new SampleService();

		// Called after creation of SampleService.
		PowerMock.replay(SampleServiceHelper2.class);

		// We can use EasyMock to replay plain mocks.
		EasyMock.expect(sampleServiceHelper2Mock.helperMethod()).andReturn("mockedHelperMethod");

		// Again since sampleServiceHelper2Mock is mocked with EasyMock and we can
		// replay it with
		// EasyMock. Notice that even though "sampleService" is partially mocked by
		// PowerMock
		// we still can replay it using EasyMock.
		EasyMock.replay(sampleServiceHelper2Mock);

		// ------ Do actually call the method to be tested and do necessary assertions.
		String result = sampleService.method3();

		assertEquals("method3:" + "mockedHelperMethod", result);

		// ------ Do necessary verification to make sure that all methods we expected
		// to be called are called

		// Verification of Class mocks need to go through PowerMock, just like in
		// replay.
		PowerMock.verify(SampleServiceHelper2.class);

		EasyMock.verify(sampleServiceHelper2Mock);
	}

	@Test
	public void testMethod4() throws Exception {
		// Since we want to mock "privateMethod" we need to
		// partially mock SampleService with PowerMock.
		//
		// Also notice that we are not calling createPartialMock with constructor
		// arguments (like in testMethod5) since method4 does not use
		// SampleServiceHelper.
		SampleService sampleService = PowerMock.createPartialMock(SampleService.class, "privateMethod",
				new Class[] { String.class });

		PowerMock.expectPrivate(sampleService, "privateMethod", "someParam").andReturn("mockedPrivateMethod");

		// Notice that even though "sampleService" is partially mocked by PowerMock
		// we still can replay it using EasyMock.
		EasyMock.replay(sampleService);

		// ------ Do actually call the method to be tested and do necessary assertions.
		String result = sampleService.method4();

		assertEquals("method4:" + "mockedPrivateMethod", result);

		// ------ Do necessary verification to make sure that all methods we expected
		// to be called are called

		EasyMock.verify(sampleService);
	}

	@Test
	public void testMethod5() throws Exception {
		// This will mock the SampleUtils static method calls.
		PowerMock.mockStatic(SampleUtils.class);

		// Here are going to tell PowerMock to replace calls to new SampleServiceHelper
		// in SampleService with sampleServiceHelperMock.
		SampleServiceHelper sampleServiceHelperMock = EasyMock.createMock(SampleServiceHelper.class);
		// Here we tell PowerMock to intercept all creations of SampleServiceHelper
		PowerMock.expectNew(SampleServiceHelper.class).andReturn(sampleServiceHelperMock);
		// PowerMock requires us to call this. Otherwise it will fail to intercept call
		// to
		// "new" SampleServiceHelper. Cant use EasyMock here since it does not support
		// mocking Class objects.
		PowerMock.replay(SampleServiceHelper.class);

		// We need to create SampleService after the above call because
		// SampleServiceHelper is create during SampleService initialization
		// "method3" will show you that you dont need to do this when object to be
		// mocked
		// is created inside method that is tested.
		//
		// Since we want to mock "privateMethod" (which is private) we need to
		// partially mock SampleService with PowerMock.
		//
		// Also notice that we are calling createPartialMock with constructor
		// arguments since plain createPartialMock wont "by default" invoke constructor
		// and
		// do class initialization which will prevent us from initializing
		// sampleServiceHelper
		// variable.
		SampleService sampleService = PowerMock.createPartialMock(SampleService.class, "privateMethod",
				new Class[] { String.class }, new Object[] {}, new Class[] {});

		PowerMock.expectPrivate(sampleService, "privateMethod", "someParam").andReturn("mockedPrivateMethod");

		// Here we are telling what should happens when SampleUtils.staticMethod()
		// gets called. We can use EasyMock to do the expectation even though PowerMock
		// is mocking the static method call.
		EasyMock.expect(SampleUtils.staticMethod()).andReturn("mockedStaticMethod");

		// We need to have PowerMock replay SampleUtils.just like its done
		// with SampleServiceHelper.class
		PowerMock.replay(SampleUtils.class);

		// We can use EasyMock to replay plain mocks.
		EasyMock.expect(sampleServiceHelperMock.helperMethod()).andReturn("mockedHelperMethod");

		// Again since sampleServiceHelper is mocked with EasyMock, we can replay it
		// with
		// EasyMock. Notice that even though "sampleService" is partially mocked by
		// PowerMock
		// we still can replay it using EasyMock.
		EasyMock.replay(sampleServiceHelperMock, sampleService);

		// ------ Do actually call the method to be tested and do necessary assertions.
		String result = sampleService.method5();

		assertEquals("method5:" + "mockedStaticMethod" + "mockedPrivateMethod" + "mockedHelperMethod", result);

		// ------ Do necessary verification to make sure that all methods we expected
		// to be called are called

		// Verification of Class mocks need to go through PowerMock, just like in
		// replay.
		PowerMock.verify(SampleUtils.class, SampleServiceHelper.class);

		EasyMock.verify(sampleServiceHelperMock, sampleService);
	}
}

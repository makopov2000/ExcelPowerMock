package com.powermock.test1;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
 
/**
*
* @author John Yeary
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest({IdentityUtilities.class, Person.class})
public class PersonTest {
 
public PersonTest() {
}
 
/**
* Test of initialize method, of class Person.
*/
@Test
public void testInitialize() {
System.out.println("initialize");
PowerMockito.mockStatic(IdentityUtilities.class); 
when(IdentityUtilities.getUUID()).thenReturn("ABC-123");
Person instance = new Person();
instance.initialize();
String result = instance.getId();
assertEquals(result, "ABC-123");
}
 
@Test
public void testGenerateId() throws Exception {
System.out.println("generateId()");
Person instance = PowerMockito.spy(new Person());
 
/*
* Setup the expectation to the private method using the method name
*/
PowerMockito.when(instance, "generateId").thenReturn("UNIT-1A");
 
instance.initialize();
 
assertEquals("UNIT-1A", instance.getId());
 
// Optionally verify that the private method was actually called
verifyPrivate(instance).invoke("generateId");
 
}
 
@Test
public void ObjectsNotEqual() {
System.out.println("Objects not Equal");
Person p = new Person();
Object o = new Object();
assertFalse(p.equals(o));
}
 
}

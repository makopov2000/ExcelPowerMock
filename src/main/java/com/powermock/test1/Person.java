package com.powermock.test1;

import java.text.MessageFormat;
import java.util.Objects;
 
/**
*
* @author John Yeary
*/
public class Person {
 
private String id;
private String firstName;
private String lastName;
 
public Person() {
}
 
public void initialize() {
if (null == id) {
id = generateId();
}
}
 
private String generateId() {
return IdentityUtilities.getUUID();
}
 
public String getFirstName() {
return firstName;
}
 
public void setFirstName(String firstName) {
this.firstName = firstName;
}
 
public String getLastName() {
return lastName;
}
 
public void setLastName(String lastName) {
this.lastName = lastName;
}
 
public String getId() {
return id;
}
 
public String getFullName() {
return MessageFormat.format("{0} {1}", firstName, lastName);
}
 
@Override
public int hashCode() {
int hash = 7;
hash = 97 * hash + Objects.hashCode(this.id);
hash = 97 * hash + Objects.hashCode(this.firstName);
hash = 97 * hash + Objects.hashCode(this.lastName);
return hash;
}
 
@Override
public boolean equals(Object obj) {
if (obj == null) {
return false;
}
if (getClass() != obj.getClass()) {
return false;
}
final Person other = (Person) obj;
if (!Objects.equals(this.id, other.id)) {
return false;
}
if (!Objects.equals(this.firstName, other.firstName)) {
return false;
}
if (!Objects.equals(this.lastName, other.lastName)) {
return false;
}
return true;
}
 
@Override
public String toString() {
return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
}
 
}

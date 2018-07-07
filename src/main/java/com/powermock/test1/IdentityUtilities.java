package com.powermock.test1;

import java.util.UUID;

/**
*
* @author John Yeary
*/
public class IdentityUtilities {
 
/**
* Generates a {@link UUID}
*
* @return a {@code String} representation of a UUID.
*/
public static String getUUID() {
return UUID.randomUUID().toString();
}
}

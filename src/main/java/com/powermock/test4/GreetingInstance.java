package com.powermock.test4;

public class GreetingInstance {
    public String getGreeting(String name) {
        return String.format(
            "%s, %s"
          , HelloStatic.getHello(), name);
    }
}

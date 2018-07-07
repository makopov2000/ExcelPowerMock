package com.powermock;

public class DefaultUserService implements UserService {

	  @Override
	  public User getUserById(Long userId) {
	    return null;
	  }

	  @Override
	  public void updateUserDetails(User newUserDetails) {
	  }

	  @Override
	  public void createUser(User user) {
	  }

	  @Override
	  public Long getUserCount() {
	    throw new UnsupportedOperationException("Not implemented");
	  }
	}
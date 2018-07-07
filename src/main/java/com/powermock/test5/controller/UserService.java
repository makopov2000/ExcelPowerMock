package com.powermock.test5.controller;

public interface UserService {
	
	/**
	 * Get user id.
	 * @param userId user id
	 * @return Retrieved user information
	 */
	User getUserById(Long userId);
	
	/**
	 * Update user details.
	 * @param newUserDetails New user details.
	 */
	void updateUserDetails(User newUserDetails);
	
	/**
	 * Create new user.
	 * @param user new user.
	 */
	void createUser(User user);
	
	/**
	 * Get the number of users.
	 * @return user count.
	 */
	Long getUserCount();

}
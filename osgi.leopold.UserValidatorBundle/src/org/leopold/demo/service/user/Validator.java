package org.leopold.demo.service.user;

/**
 * desc: user login service validator
 * @author leopold
 *
 */
public interface Validator {
/**
 * According username && password to validate whether user could login in  
 * @param username
 * @param password
 * @return
 */
	public boolean validator(String username, String password);
}

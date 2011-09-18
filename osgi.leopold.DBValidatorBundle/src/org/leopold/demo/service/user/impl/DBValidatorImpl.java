package org.leopold.demo.service.user.impl;

import org.leopold.demo.service.user.Validator;

public class DBValidatorImpl implements Validator {

	@Override
	public boolean validator(String username, String password) {
		System.out.println("DB Validator");
		if("leopold".equals(username)&&"abc".equals(password)){
			return true;
		}
		return false;
	}

}

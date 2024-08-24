package com.makersharks.suppliersearch.service;

import com.makersharks.suppliersearch.dto.LoginDto;

public interface AuthService {

	String login(LoginDto loginDto);
}

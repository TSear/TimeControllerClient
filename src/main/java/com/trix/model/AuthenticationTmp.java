package com.trix.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationTmp {

	private boolean loginFlag = false;
	private Long id = 0l;
	private String token;
}

package com.trix.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationWrapper {
	
	private List<ApplicationModel> applicationList = new ArrayList<>();

}

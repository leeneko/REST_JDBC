package com.leeneko.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserIn {
	
	private String factoryCode;
	private String userId;
	private String fromDate;
	private String toDate;

}

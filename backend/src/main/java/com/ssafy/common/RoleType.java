package com.ssafy.common;

import lombok.Getter;

@Getter
public enum RoleType {
	SUPERVISOR(1), MEMBER(2), VISITOR(3);

	private int roleType;

	RoleType(int roleType) {
		this.roleType = roleType;
	}
}

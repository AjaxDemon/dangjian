package com.psbc.wyk.dangjian.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class Account extends User {

	private Long accountId;

	public Account(Long accountId, String username, String password,
                   Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.accountId = accountId;
	}
}

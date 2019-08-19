package dev.iteducation.iteducation.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
@NoArgsConstructor
public class UserAccount implements UserDetails {

	private static final long serialVersionUID = 6609435983980126435L;

	@Getter
	@Setter
	private String email;

	@Setter
	private String username;

	private String password;

	@Setter
	private Boolean enabled;

	@Setter
	@Getter
	private List<UserRole> roles;

	public UserAccount(String email, String username, String password, Boolean enabled, List<UserRole> roles) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toList());
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}

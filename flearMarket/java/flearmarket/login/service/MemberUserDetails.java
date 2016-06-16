package flearmarket.login.service;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.userdetails.UserDetails;

import flearmarket.login.service.Role; 


public class MemberUserDetails implements UserDetails  {		// 역할 : UserDAO 디테일 정보를 관리하는 클래스

private static final long serialVersionUID = 1L;
	
	private String username; 
	private String password;
	private int userno; 
	

	    private List<Role> authorities;
	    private boolean accountNonExpired = true;
	    private boolean accountNonLocked = true;
	    private boolean credentialsNonExpired = true;
	    private boolean enabled = true;
	    
	    
	    public int getUserno() {
	        return userno;
	    }
	    
	    
	    public void setUserno(int userno) {
	        this.userno = userno;
	    }
	    
	    @Override
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return this.authorities;
	    }

	    public void setAuthorities(List<Role> authorities) {
	        this.authorities = authorities;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return this.accountNonExpired;
	    }

	    public void setAccountNonExpired(boolean accountNonExpired) {
	        this.accountNonExpired = accountNonExpired;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return this.accountNonLocked;
	    }

	    public void setAccountNonLocked(boolean accountNonLocked) {
	        this.accountNonLocked = accountNonLocked;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return this.credentialsNonExpired;
	    }

	    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
	        this.credentialsNonExpired = credentialsNonExpired;
	    }

	    @Override
	    public boolean isEnabled() {
	        return this.enabled;
	    }

	    public void setEnabled(boolean enabled) {
	        this.enabled = enabled;
	    }

	
}

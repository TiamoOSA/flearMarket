package flearmarket.login.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.AuthenticationException; 
import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import flearmarket.login.dao.UserDAO;
import flearmarket.login.service.MemberService;
import flearmarket.login.service.MemberUserDetails;


@Service("memberAuthenticationProvider")
public class MemberAuthenticationProvider implements AuthenticationProvider {				// 세션 제공 및 보안 관리 클래스 
	
private static final Logger logger = LoggerFactory.getLogger(MemberAuthenticationProvider.class);
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="memberService")
    MemberService memberService;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {		// 세션 
		// TODO Auto-generated method stub
		String user_id = (String)authentication.getPrincipal();		 
		String user_pw = (String)authentication.getCredentials(); 

		 MemberUserDetails user;
	        Collection<? extends GrantedAuthority> authorities;
	    
	     
	        try {

	            user = (MemberUserDetails)memberService.loadUserByUsername(user_id);

	          /*  String hashedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(user));

	            logger.info("username : " + username + " / password : " + password + " / hash password : " + hashedPassword);
	            logger.info("username : " + user.getUsername() + " / password : " + user.getPassword());
	           
	            if (!hashedPassword.equals(user.getPassword())) throw new BadCredentialsException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
	           */
	         if (!user_pw.equals(user.getPassword())) throw new BadCredentialsException("password is not match!");

	            authorities = user.getAuthorities();
	        } catch(UsernameNotFoundException e) {
	            logger.info(e.toString());
	            throw new UsernameNotFoundException(e.getMessage());
	        } catch(BadCredentialsException e) {
	            logger.info(e.toString());
	            throw new BadCredentialsException(e.getMessage());
	        } catch(Exception e) {
	            logger.info(e.toString());
	            throw new RuntimeException(e.getMessage());
	        }

	   return new UsernamePasswordAuthenticationToken(user, user_pw, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

package flearmarket.login.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import flearmarket.login.service.MemberUserDetails;
import flearmarket.login.service.Role;
import flearmarket.login.dao.UserDAO;

@Service("memberService")
public class MemberService implements UserDetailsService  {				// 유저와 관련된 서비스를 제공하는 클래스 
																		
Logger log = Logger.getLogger(this.getClass());
    
	@Resource(name="userDAO")
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		String password=userDAO.getUserPwd(username);
		
		MemberUserDetails user = new MemberUserDetails();
        user.setUsername(username);
        
        if (password== null) throw new UsernameNotFoundException("������ ������ ã�� �� �����ϴ�.");
        
        user.setPassword(password);
        user.setUserno(userDAO.getUserno(username));
       
        Role role = new Role();
        role.setName("ROLE_USER");

        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setAuthorities(roles);
        
        return user;
		
	}

	public void insertMember(Map<String, Object> map) throws Exception{
		userDAO.insertMember(map);
		
	}
	


	public boolean checkMemberPresent(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Map<String, Object>> selectMemberList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.selectMemberList(map);
	}
	
	

	
	public int getUserno(String userid) throws Exception{
		return userDAO.getUserno(userid);
	}

	public List<Map<String, Object>>showStandbyYou(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDAO.selectStandbyYou(map);
	}

	public List<Map<String, Object>> showStandbyOther(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDAO.selectStandbyOther(map);
	}
	
	
	
}

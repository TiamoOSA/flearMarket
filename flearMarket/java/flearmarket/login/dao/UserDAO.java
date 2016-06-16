package flearmarket.login.dao;


import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import flearmarket.common.dao.AbstractDAO;



@Repository("userDAO")
public class UserDAO extends AbstractDAO {
	 public String getUserPwd(String userid) {
		 	return (String)selectOne("user.getUserPwd",userid);
	}
	 
	 public int getUserno(String userid){
		 return (Integer)selectOne("user.getUserno",userid);
	 }

	public void insertMember(Map<String, Object> map) throws Exception{
		insert("user.insertMember", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserList() throws Exception{
		return (List<Map<String, Object>>)selectList("user.selectUserList");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMemberList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("user.selectRelationList",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMemberListForRelation(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("user.selectRelationListForRelation",map);
	}
	
	public void deleteRelation(Map<String, Object> map) throws Exception{
		delete("user.deleteRelation", map);
		
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectrelationDetail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectOne("user.selectRelationDetail",map);
	
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectStandbyYou(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>)selectList("user.selectStandbyYou",map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectStandbyOther(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>)selectList("user.selectStandbyOther",map);
	}
	public void insertRelation(Map<String, Object> map) {
		insert("user.insertRelation", map);
	}


	public void QuartzTest() {
		log.debug("Quartz 작업 테스트중");
		
	}

	
	public void insertRelationStandby(Map<String, Object> map) {
		insert("user.insertRelationStandby", map);
	}
	
	public void deleteRelationStandby(Map<String, Object> map) throws Exception{
		delete("user.deleteRelationStandby", map);
		
	}

	public void insertRelationUseStandby(Map<String, Object> map) {
		insert("user.insertRelationUseStandby", map);
		
	}

	public void deleteRelationStandbyR(Map<String, Object> map) {
		delete("user.deleteRelationStandbyR", map);
		
	}

	public String getRelationStandby(Map<String, Object> map) {
		return (String)selectOne("user.getRelationStandby",map);
	}
}

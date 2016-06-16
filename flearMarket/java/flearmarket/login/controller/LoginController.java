package flearmarket.login.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import flearmarket.common.common.CommandMap;
import flearmarket.login.service.MemberService;
import flearmarket.login.service.MemberUserDetails;

@Controller
public class LoginController {

	@Resource(name="memberService")
	private MemberService memberService;		
	
	
	
	@RequestMapping(value="/login/login.do")											// 로그인 페이지
	public ModelAndView login(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/login/login");
    	    	
    	return mv;
	}
	
	@RequestMapping(value="/login/loginFail.do")										// 로그인 실패 페이지 
	public ModelAndView loginFail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/login/loginFail");
    	   	
    	return mv;
	}
	
	@RequestMapping(value="main.do")			
	public ModelAndView main(CommandMap commandMap, Principal principal, HttpSession session) throws Exception{
		
		ModelAndView mv = new ModelAndView("main");
		String name = principal.getName();

		mv.addObject("username", name);
		return mv;
	}
	
	@RequestMapping(value="/login/logout.do")
	public ModelAndView logout(CommandMap commandMap, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("/login/logout");    	
		return mv;
	}
	
	
	@RequestMapping(value ="/login/openmemberJoin.do")								// 회원가입 페이지로 이동
	public ModelAndView openMemberJoin(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/login/memberJoin");
	    	
	    return mv;
	    	
	}
	
	@RequestMapping(value = "/login/insertmemberJoin.do")
	public ModelAndView insertMember(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/login/login.do");
		if(!memberService.checkMemberPresent(commandMap.getMap()));
	
		memberService.insertMember(commandMap.getMap()); 
		
		int userno=memberService.getUserno((String)commandMap.get("ID"));
		commandMap.put("userno", userno);
	     commandMap.put("nickname", "Me");
    
		return mv;
	    	
	}
	
	
	
	
}

package flearmarket.email.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.chain.Command;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import flearmarket.board.service.BoardService;
import flearmarket.common.common.CommandMap;
import flearmarket.email.service.EmailNotificationService;

@Controller
public class EmailController{
	Logger log = Logger.getLogger(this.getClass());
	 
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="emailNotificationService")	
	private EmailNotificationService emailNotificationService;
	
	
	@RequestMapping(value="/email/EmailNotiyToAdmin.do")
	public ModelAndView sendEmail( String userName,  String userEmailAddress, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/board/openBoardDetail.do");
		
		boardService.saleComplete(commandMap.getMap());
		
		
		
		emailNotificationService.sendEmail(userName, userEmailAddress, commandMap);
	
		
		return mv;
		
	}
	
	
	
}

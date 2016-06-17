package flearmarket.email.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.chain.Command;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import flearmarket.login.dao.UserDAO;
import flearmarket.common.common.CommandMap;


 
@Service("emailNotificationService")
public class EmailNotificationServiceImpl implements EmailNotificationService {
	Logger log = Logger.getLogger(this.getClass()); 
	String UserName; 								// 수정 필요, DB에서 유저 이름과 관리자 이메일을 가져온뒤 메일링 할 것인지, 아니면 jsp 에서 바로 할 것인지.. 		
		
	String AdminEmailAddress;
	
	@Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private VelocityEngine velocityEngine;

	@Autowired
	private UserDAO userDAO;
    
    
    public void setMailSender(JavaMailSender mailSender){
		this.mailSender = mailSender;
	}
    
    //Q! non setVelocityEngine is error occur to context-email.xml at bean property. why?
    public void setVelocityEngine(VelocityEngine velocityEngine){
		this.velocityEngine = velocityEngine;
	}
   
    
	@Override
	public void sendEmail(final String userName, final String AdminEmailAddress, final CommandMap commandMap) throws Exception{
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
			@Override
				public void prepare(final MimeMessage mimeMessage) throws Exception {			             
			 		// MimeMessageHelper(1,2,3) 3th encoding argument necessary!!! 
			 		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,false, "UTF-8");
			 		Map<String, Object> model = new HashMap();
			 		List<Map<String, Object>> userListMap = userDAO.selectUserList();					// 현재 유저정보를 가져온다.
			 		
			 		
			 		model.put("ItemMap", commandMap);													/* This is used in .vm file, access ${ItemMap.keyset()} / ${ItemMap.entryset()} ...
														 													 * ex>
														 													 *     ${ItemMap} 				: model.get("ItemMap") = targetItemMap 
														 													 *     ${ItemMap.keySet}		: targetItemMap.keyset() = { yogurt, milk, bread, juice.. }
														 													 *     ${yogurt.get($yogurt)}	: targetItemMap.get("yogurt") = value
														 													 */
			 		
						
			 		mimeMessageHelper.setTo("flearmarket@gmail.com");
			 		mimeMessageHelper.setSubject( "jinsu" + " 님 께서 기부완료하셨습니다.");

			   /* Debugging	- wrong use case.
			    * 
			    * VelocityContext contexct = new VelocityContext();
			    * Map<String, Object> foodItemMap = new HashMap<>();
			 	*	for(String foodName : targetItemMap.keySet()){
			 	*		foodItemMap.put(foodName, targetItemMap.get(foodName) );			
				* }
				*  log.debug("FoodItemMap info : " +foodItemMap.entrySet());
				*   contexct.put("targetFood", foodItemMap);
				*    
			 	*	//  vm에서 ketset을 못찾냐
			 	*  String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "foo.vm", "utf-8", targetItemMap);
			 	*/
				    String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "EmailNotifyToAdmin.vm", "utf-8", model);
				    mimeMessageHelper.setText(text, true);
			 		}// end preapre method
			  	
		  };
		  		log.debug("User Name  :" + userName);
		  		log.debug("Admin address :"+ "flearmarket@gmail.com" );					// 관리자 이메일 처리 수정 필요
			  	log.debug("ItemMap info  :" + commandMap.entrySet());
			  	this.mailSender.send(preparator);
			  	
	}
  }
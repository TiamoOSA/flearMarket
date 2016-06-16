package flearmarket.email.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.chain.Command;

import flearmarket.common.common.CommandMap;


public interface EmailNotificationService {


	void sendEmail(final String userName, final String userEmailAddress, final CommandMap commandMap) throws Exception;

}

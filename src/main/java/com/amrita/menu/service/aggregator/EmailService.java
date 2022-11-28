package com.amrita.menu.service.aggregator;

import com.amrita.menu.service.model.EmailDetails;

public interface EmailService {

	 String sendSimpleMail(EmailDetails details);
}

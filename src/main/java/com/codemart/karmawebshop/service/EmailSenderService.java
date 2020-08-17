package com.codemart.karmawebshop.service;

import com.codemart.karmawebshop.entity.Mail;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSenderService {
    void sendEmail(Mail mail)throws MessagingException, IOException;
}

package com.violet.ocpc.web.common.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.violet.ocpc.web.holder.Email;

/**
 * TODO To describe the functionality of this method
 *
 * @author HYJ
 */
public class EmailUtil
{
    public static void sendMail(Email adminMail, String subject, String content, String to) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", adminMail.getEmailSmtpHost());
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.auth", "true");
            
            Session session = Session.getInstance(props);
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(adminMail.getEmailAddress()));
            message.setRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");
            Transport transport = session.getTransport();
            transport.connect(adminMail.getEmailSmtpHost(), adminMail.getEmailAddress(), adminMail.getEmailPwd());
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("邮件发送失败...");
        }
    }
}

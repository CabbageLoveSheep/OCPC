package com.violet.ocpc.web.config;

import java.beans.PropertyVetoException;

import com.violet.ocpc.web.holder.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminEmailConfig
{
    @Value("${admin.email.address}")
    private String adminEmailAddress;
    
    @Value("${admin.email.password}")
    private String adminEmailPwd;
    
    @Value("${admin.email.smtp.host}")
    private String adminSmtpHost;

    @Bean(name = "adminEmail")
    public Email createAdminEmail() throws PropertyVetoException {
        Email adminEmail = new Email();
        adminEmail.setEmailAddress(adminEmailAddress);
        adminEmail.setEmailPwd(adminEmailPwd);
        adminEmail.setEmailSmtpHost(adminSmtpHost);
        return adminEmail;
    }
}

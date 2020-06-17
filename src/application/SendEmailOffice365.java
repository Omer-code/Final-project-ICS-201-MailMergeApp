package application;


import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailOffice365 {

	private String AccountName;
	private String Password;
    	private String from;
	private String to;
    	private String subject;
	private String messageContent;


	public SendEmailOffice365(String AccountName,String Password,String to,
			String subject, String messageContent){
		this.AccountName = AccountName;
		this.from = AccountName;
		this.Password = Password;
		this.to = to;
		this.messageContent = messageContent;
		this.subject = subject;
	}

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private static final String SMTP_Server = "smtp.kfupm.edu.sa";
    private static final int SMTP_Port = 587;

    public boolean sendEmail() {
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(AccountName, Password);
            }

        });

        try {
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(messageContent);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Error Sending Message: " + ex.getMessage(), ex);
            return false;
        }
        return true;
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SMTP_Server);
        config.put("mail.smtp.port", SMTP_Port);
        return config;
    }
}
package vn.edu.ktqt.business;

import vn.edu.ktqt.data.Env_24110179;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService_24110179 {
    public void sendOtp(String to, String otp) throws MessagingException {
        if (!"true".equalsIgnoreCase(Env_24110179.get("MAIL_ENABLED"))) {
            return;
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", Env_24110179.get("MAIL_HOST"));
        properties.put("mail.smtp.port", Env_24110179.get("MAIL_PORT"));

        String username = Env_24110179.get("MAIL_USERNAME");
        String password = Env_24110179.get("MAIL_PASSWORD");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Env_24110179.get("MAIL_FROM")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(Env_24110179.get("MAIL_SYSTEM_NAME") + " - OTP đăng ký");
        message.setText("Mã OTP đăng ký Book Store của bạn là: " + otp);
        Transport.send(message);
    }
}

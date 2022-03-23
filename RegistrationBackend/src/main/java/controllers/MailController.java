package controllers;

import models.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

// Здесь будем отправлять наше письмо
public class MailController {
    // Создаём метод отправки, принимающий нашего юзера
    public static void SendMail(User user) throws MessagingException {
        // Биндим SMTP-клиента
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        // Создаём сессию для аутентикации с паролем
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yourgmail@gmail.com", "yourpassword");
            }
        });
        // Создаём наше сообщение
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("yourgmail@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.Login));
        message.setSubject("Registration");
        String msg = "You have been successfully registered!";
        // Создаём тело сообщения
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        // Отправляем эту сволочь
        Transport.send(message);
    }
}
// Ценок

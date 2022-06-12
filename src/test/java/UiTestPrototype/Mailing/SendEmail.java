package UiTestPrototype.Mailing;

import UiTestPrototype.General.generalFunction;
import UiTestPrototype.configs.Constants;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

@Component
public class SendEmail extends generalFunction {
    public static void main(String[] args) throws EmailException {

        System.out.println("====================== Email started ======================");
        sendingEmail();
        System.out.println("====================== Email sent ======================");

    }

    public static void sendingEmail() throws EmailException {
        System.out.println("====================== Email started ======================");
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        //email.setAuthenticator(new DefaultAuthenticator("iheb1athimni@gmail.com","3310Danger3310"));
        email.setSSLOnConnect(true);
        email.setAuthentication("iheb1athimni@gmail.com","3310Danger3310");
        email.setFrom("iheb1athimni@gmail.com");
        email.setSubject(/* prop.getProperty("navigator") +*/ "Browser test automation");
        email.setMsg("this is test automation project : testing authentification using "/*+prop.getProperty("navigator") */+"browser ");
        email.addTo("iheb1athimni@gmail.com");
        email.send();
        System.out.println("====================== Email sent ======================");
    }


    public static void sendingEmailWithAttachment(List<String> tos,String subject, String text, String Rapport_Path, String smtpHost, String smtpPort) throws EmailException {
        Session session = authentificationSession(smtpHost,smtpPort);
        tos.stream().forEach(emailTo ->{
            try {
                Message message = new MimeMessage(session);
                BodyPart messageBodyPart = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
                message.setSubject(subject);
                messageBodyPart.setText(text);
                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(Rapport_Path);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(Rapport_Path);
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                Transport.send(message);
                System.out.println(" Send Message successfully");
            }catch (MessagingException e)
            {
                System.out.println(" error Sending Message with attachment");
            }
        });
    }
    public static Session authentificationSession(String host, String Port){
        Properties props = new Properties();
        props.put(Constants.MAIL_SMTP_HOST, host);
        props.put(Constants.MAIL_SMTP_PORT, Port);
        return Session.getInstance(props,null);
    }
}

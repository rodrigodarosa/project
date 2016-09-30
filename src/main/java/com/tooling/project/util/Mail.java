package com.tooling.project.util;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.tooling.project.model.OutputBudget;

public class Mail {
	public static void sendMail(OutputBudget outputBudget) throws UnsupportedEncodingException {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new PasswordAuthentication("techtoolsferramentaria@gmail.com", "rodrigo250390");
                         }
                    });

        session.setDebug(true);

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("techtoolsferramentaria@gmail.com", "TechTools - Or\u00E7amento")); //Remetente

              Address[] toUser = InternetAddress.parse(outputBudget.getProvider().getEmail());  
              SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:ss");

              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject("Solicita\u00E7\u00E3o de Or\u00E7amento");
              message.setText("Ol\u00E1, Este \u00E9 um e-mail autom\u00E1tico!"
            		  +"\n\n\n"
            		  +"Solicita-se os seguintes produtos:"
            		  +"\n\n"
            		  +"Descri\u00E7\u00E3o: "+outputBudget.getDescription()+"\n"
            		  +"Especifica\u00E7\u00E3o T\u00E9cnica: "+outputBudget.getTechnicalSpecification()+"\n"
            		  +"Quantidade: "+outputBudget.getQuantity()+"\n"
            		  +"Data necess\u00E1ria para entrega: "+sdf.format(outputBudget.getDeliveryDate())+"\n"
            		  +"\n"
            		  +"\n"
            		  +"Qualquer Dvida, entrar em contato!"
            		  +"\n\n"
            		  +"Or\u00E7amento Solicitado por "+outputBudget.getEmployee().getName()+"\n"
            		  +"RESPONDER ESTA SOLICITA\u00C7\u00C3O DE OR\u00C7AMENTO NO SEGUINTE E-MAIL: " +outputBudget.getEmployee().getEmail()+"\n"
            		  +"\n\n\n"
            		  +"TechTools Ferramentaria."
            		  );
              Transport.send(message);

         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
  }



}
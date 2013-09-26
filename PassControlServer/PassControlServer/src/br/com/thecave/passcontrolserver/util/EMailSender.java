/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EMailSender
{
    public static void sendEMail(String dest, String subject, String content)  throws javax.mail.MessagingException
    {
        //Usuário e senha
        final String username = "thecavesoftwares@gmail.com";
        final String password = "123bimo123";

        //Configurações do servidor
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); 
        
        //Autentica usuário e senha
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator()
            {

                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(username, password);
                }
            }); 
        
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("thecavesoftwares@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO,
                        InternetAddress.parse(dest));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);        
    }
    
    public static void main(String[] args)
    {
        try {
            sendEMail("guilherme.mr89@gmail.com", "Assunto", "Conteúdo \n com quebra *****\n");
        } catch (javax.mail.MessagingException ex) {
            Logger.getLogger(EMailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
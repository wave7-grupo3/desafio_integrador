package com.group03.desafio_integrador.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
public class JavaMailService {

    // @Autowired
    // @Value("${string.datasource.email}")
    // private static String email;
    //@Autowired
//    @Value("${string.datasource.passwordemail}")
    private String password;

    /**
     * Método responsável por enviar email de pagamento
     * @param name
     * @param emailCliente
     */
    public synchronized static void sendMail(String name, String emailCliente) {
        log.info("[JAVA-MAIL] Sending email to: " + emailCliente);

        Properties props = new Properties();

        // Parâmetros de conexão com servidor Gmail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        String email = "gabrielmorais96@gmail.com";
        String password =  "towreqtnvgdztibh";

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email)); //Remetente

            Address[] toUser = InternetAddress.parse(emailCliente);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Seu Pagamento foi processado!");//Assunto
            message.setText(
                    "Olá " + name + ", seu pagamento foi aprovado!\n" +
                    "Logo em breve seu produto será enviado.\n" +
                    "Esperamos que você esteja contente com os produtos. :)\n"
            );

            Transport.send(message);
            log.info("[JAVA-MAIL] Email enviado com sucesso!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

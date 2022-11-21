package com.group03.desafio_integrador.service;

import lombok.extern.slf4j.Slf4j;

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
    //@Value("${string.datasource.passwordemail}")
    private String password;
    public static void sendMail(String name, String emailCliente) {
        log.info("[JAVA-MAIL] Sending email to: " + emailCliente);

        Properties props = new Properties();

        // Parâmetros de conexão com servidor Gmail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        String email = "gabrielmorais96@gmail.com";
        String password = "towreqtnvgdztibh";

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
            message.setSubject("Sua compra chegou, aproveite!");//Assunto
            message.setText("Olá" + name + ", sua compra chegou, aproveite!" +
                    "Fizemos a entrega na Rua José Ribeiro Filho 35, Belo Horizonte CEP 31330500." +
                    "Esperamos que você esteja contente com os produtos. Caso contrário, você pode devolvê-los até sábado 5 de novembro.\n" +
                    "\n"
            );

            Transport.send(message);
            log.info("[JAVA-MAIL] Email enviado com sucesso!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

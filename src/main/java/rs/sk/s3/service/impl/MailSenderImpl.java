package rs.sk.s3.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.sk.s3.dto.MailDto;
import rs.sk.s3.service.MailSender;

@Service
public class MailSenderImpl implements MailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

    @Async
    @Override
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(mailDto.getTo());
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText(mailDto.getBody());

        javaMailSender.send(simpleMailMessage);
        logger.info("Mail \"" + mailDto.getTitle() + "\" sent to \"" + mailDto.getTo() +"\"");
    }

}

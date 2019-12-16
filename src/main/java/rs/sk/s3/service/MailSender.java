package rs.sk.s3.service;

import rs.sk.s3.dto.MailDto;

public interface MailSender {

    void sendMail(MailDto mailDto);

}

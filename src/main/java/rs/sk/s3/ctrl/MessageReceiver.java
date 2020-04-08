package rs.sk.s3.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import rs.sk.s3.dto.MailDto;
import rs.sk.s3.service.InputChannel;
import rs.sk.s3.service.MailSender;

@MessageEndpoint
@EnableBinding(InputChannel.class)
@RequiredArgsConstructor
public class MessageReceiver {

    private final MailSender mailSender;

    @StreamListener(InputChannel.CHANNEL_NAME)
    public void receive(MailDto mailDto) throws Exception {
        mailSender.sendMail(mailDto);
    }

}

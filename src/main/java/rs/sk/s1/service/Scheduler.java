package rs.sk.s1.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.sk.s1.domain.dto.MailDto;

@Component
@EnableBinding(OutputChannel.class)
@RequiredArgsConstructor
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

    private final OutputChannel outputChannel;

    @Scheduled(fixedDelay = 21600000) // Every 6h
    public void publish() {
        MailDto mailDto = new MailDto("pgalantic17@raf.rs", "Mail Title", "This is mail body text.");
        outputChannel.output().send(MessageBuilder.withPayload(mailDto).build());
        logger.info(mailDto.toString());
    }

}

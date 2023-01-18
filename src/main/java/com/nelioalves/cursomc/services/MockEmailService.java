package com.nelioalves.cursomc.services;

import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.slf4j.Logger;

public class MockEmailService extends AbstractEmailService{

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);



    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de Email ...");
        LOG.info(msg.toString());
        LOG.info("Email enviado");


    }
}

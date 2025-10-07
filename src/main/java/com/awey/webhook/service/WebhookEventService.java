package com.awey.webhook.service;

import com.awey.webhook.entity.WebhookEvent;
import com.awey.webhook.repository.WebhookEventRepository;
import org.springframework.stereotype.Service;

@Service
public class WebhookEventService {

    private final WebhookEventRepository repository;

    public WebhookEventService(WebhookEventRepository repository) {
        this.repository = repository;
    }

    public void saveEvent(String messageId, String payload) {
        // Evitar duplicatas
        if (repository.existsByMessageId(messageId)) return;



        WebhookEvent event = new WebhookEvent();
        event.setMessageId(messageId);
        event.setPayload(payload);

        System.out.println("\n \n \n SERVICE ----------------)") ;
        System.out.println("Payload recebido: " + payload);


        repository.save(event);
    }
}
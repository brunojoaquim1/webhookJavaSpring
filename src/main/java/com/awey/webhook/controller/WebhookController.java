package com.awey.webhook.controller;
import com.awey.webhook.service.WebhookEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final WebhookEventService service;
    private final ObjectMapper objectMapper;

    public WebhookController(WebhookEventService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<String> receiveWebhook(@RequestBody String payload) {
        try {
            JsonNode json = objectMapper.readTree(payload);
            String messageId = json.path("messageId").asText();

            service.saveEvent(messageId, payload);
            return ResponseEntity.ok("Evento recebido");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao processar payload");
        }
    }

    @RestController
    public class HealthController {
        @GetMapping("/")
        public String health() {
            return "Webhook API online 🚀";
        }
    }

}
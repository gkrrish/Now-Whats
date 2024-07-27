package com.whats.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<String> receiveWebhook(@RequestBody Map<String, Object> payload) {
        System.out.println("Webhook received: " + payload);

        // Extract relevant information from the payload (e.g., message status updates)
        // Note: You should check and cast payload correctly based on the actual structure
        if (payload.containsKey("entry")) {
            var entries = (List<Map<String, Object>>) payload.get("entry");
            for (var entry : entries) {
                var changes = (List<Map<String, Object>>) entry.get("changes");
                for (var change : changes) {
                    var value = (Map<String, Object>) change.get("value");
                    if (value.containsKey("statuses")) {
                        var statuses = (List<Map<String, Object>>) value.get("statuses");
                        for (var status : statuses) {
                            String messageId = (String) status.get("id");
                            String statusString = (String) status.get("status");
                            System.out.println("Message ID: " + messageId + " Status: " + statusString);
                            // Handle the status update (e.g., update your database, notify the user, etc.)
                        }
                    }
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("Webhook received and processed");
    }
}

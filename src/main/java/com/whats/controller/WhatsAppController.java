package com.whats.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whats.service.WhatsAppServiceClient;

@RestController
public class WhatsAppController {

    @Autowired
    private WhatsAppServiceClient whatsAppService;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String to, @RequestParam String message) {
        try {
            return whatsAppService.sendMessage(to, message);
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}


package ru.itupolev.hackaton.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itupolev.hackaton.controller.dto.EmailBroadcastDto;
import ru.itupolev.hackaton.service.BroadcastService;

@RestController
@RequestMapping("/api/mail")
public class EmailController {

    private final BroadcastService broadcastService;

    public EmailController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PostMapping("/broadcast")
    public ResponseEntity<?> sendBroadcast(@RequestBody @Valid EmailBroadcastDto request) {
        broadcastService.sendBroadcast(request);
        return ResponseEntity.ok().build();
    }
}
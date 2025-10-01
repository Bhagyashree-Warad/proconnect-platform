package com.proconnect.controller;

import com.proconnect.dto.chat.ChatMessageRequestDTO;
import com.proconnect.dto.chat.ChatMessageResponseDTO;
import com.proconnect.service.ChatMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;

    // Send a message
    @PostMapping("/send")
    public ResponseEntity<ChatMessageResponseDTO> sendMessage(
            @Valid @RequestBody ChatMessageRequestDTO request) {
        ChatMessageResponseDTO response = chatMessageService.sendMessage(request);
        return ResponseEntity.ok(response);
    }

    // Get messages between two users
    @GetMapping("/between")
    public ResponseEntity<List<ChatMessageResponseDTO>> getMessagesBetweenUsers(
            @RequestParam Long senderId,
            @RequestParam Long receiverId) {
        List<ChatMessageResponseDTO> messages = chatMessageService.getMessagesBetweenUsers(senderId, receiverId);
        return ResponseEntity.ok(messages);
    }

    // Get all messages for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatMessageResponseDTO>> getMessagesForUser(
            @PathVariable Long userId) {
        List<ChatMessageResponseDTO> messages = chatMessageService.getMessagesForUser(userId);
        return ResponseEntity.ok(messages);
    }
}

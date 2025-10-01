package com.proconnect.service;

import com.proconnect.dto.chat.ChatMessageRequestDTO;
import com.proconnect.dto.chat.ChatMessageResponseDTO;
import com.proconnect.entity.ChatMessage;
import com.proconnect.entity.User;
import com.proconnect.enums.ChatType;
import com.proconnect.enums.MessageStatus;
import com.proconnect.repository.ChatMessageRepository;
import com.proconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    // Create message
    public ChatMessageResponseDTO sendMessage(ChatMessageRequestDTO request) {
        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        ChatMessage message = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .messageContent(request.getMessageContent())
                .status(request.getStatus())
                .chatType(request.getChatType())
                .createdAt(LocalDateTime.now())
                .build();

        ChatMessage saved = chatMessageRepository.save(message);
        return mapToResponse(saved);
    }

    // Get messages between two users
    public List<ChatMessageResponseDTO> getMessagesBetweenUsers(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        return chatMessageRepository.findBySenderAndReceiver(sender, receiver)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get all messages for a user
    public List<ChatMessageResponseDTO> getMessagesForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return chatMessageRepository.findByReceiver(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Mapping method
    private ChatMessageResponseDTO mapToResponse(ChatMessage msg) {
        return ChatMessageResponseDTO.builder()
                .id(msg.getId())
                .senderId(msg.getSender().getId())
                .senderName(msg.getSender().getFirstName() + " " + msg.getSender().getLastName())
                .receiverId(msg.getReceiver().getId())
                .receiverName(msg.getReceiver().getFirstName() + " " + msg.getReceiver().getLastName())
                .messageContent(msg.getMessageContent())
                .status(msg.getStatus())
                .chatType(msg.getChatType())
                .createdAt(msg.getCreatedAt())
                .build();
    }
}

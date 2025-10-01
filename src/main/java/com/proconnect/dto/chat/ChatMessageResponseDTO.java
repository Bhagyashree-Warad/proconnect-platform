package com.proconnect.dto.chat;

import com.proconnect.enums.ChatType;
import com.proconnect.enums.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageResponseDTO {

    private Long id;

    private Long senderId;
    private String senderName;

    private Long receiverId;
    private String receiverName;

    private String messageContent;

    private MessageStatus status;
    private ChatType chatType;

    private LocalDateTime createdAt;
}

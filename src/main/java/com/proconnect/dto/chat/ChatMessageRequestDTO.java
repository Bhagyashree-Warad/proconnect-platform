package com.proconnect.dto.chat;

import com.proconnect.enums.ChatType;
import com.proconnect.enums.MessageStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageRequestDTO {

    @NotNull(message = "Sender ID is required")
    private Long senderId;

    @NotNull(message = "Receiver ID is required")
    private Long receiverId;

    @NotBlank(message = "Message content is required")
    private String messageContent;

    @NotNull(message = "Message status is required")
    private MessageStatus status;

    @NotNull(message = "Chat type is required")
    private ChatType chatType;
}

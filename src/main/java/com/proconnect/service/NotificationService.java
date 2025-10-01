package com.proconnect.service;

import com.proconnect.dto.notification.NotificationRequestDTO;
import com.proconnect.dto.notification.NotificationResponseDTO;
import com.proconnect.entity.Notification;
import com.proconnect.entity.User;
import com.proconnect.repository.NotificationRepository;
import com.proconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    // CREATE NOTIFICATION
    public NotificationResponseDTO createNotification(NotificationRequestDTO request) {
        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        Notification notification = Notification.builder()
                .type(request.getType())
                .message(request.getMessage())
                .user(user)
                .build();

        Notification saved = notificationRepository.save(notification);
        return mapToDTO(saved);
    }

    // GET BY ID
    public NotificationResponseDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        return mapToDTO(notification);
    }

    // GET ALL
    public List<NotificationResponseDTO> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET UNREAD BY USER
    public List<NotificationResponseDTO> getUnreadNotificationsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return notificationRepository.findByIsReadFalseAndUser(user).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // MARK AS READ
    @Transactional
    public NotificationResponseDTO markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        notification.setRead(true);
        return mapToDTO(notification);
    }

    // DELETE
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new IllegalArgumentException("Notification not found");
        }
        notificationRepository.deleteById(id);
    }

    // HELPER: map entity → DTO
    private NotificationResponseDTO mapToDTO(Notification notification) {
        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .type(notification.getType())
                .message(notification.getMessage())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .userId(notification.getUser() != null ? notification.getUser().getId() : null)
                .userName(notification.getUser() != null ? notification.getUser().getFirstName() + " " + notification.getUser().getLastName() : null)
                .build();
    }
}

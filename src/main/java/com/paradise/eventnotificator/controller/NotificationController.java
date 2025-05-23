package com.paradise.eventnotificator.controller;

import com.paradise.eventnotificator.domain.entity.Notification;
import com.paradise.eventnotificator.dto.NotificationDto;
import com.paradise.eventnotificator.dto.NotificationRequest;
import com.paradise.eventnotificator.security.jwt.CustomPrincipal;
import com.paradise.eventnotificator.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {


    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotification(
            @AuthenticationPrincipal CustomPrincipal customPrincipal
            ) {
        Long id = customPrincipal.getId();
        List<Notification> notReadyUserNotifications =
                notificationService.getAllNotReadyUserNotifications(id);
        List<NotificationDto> notificationsResponse = notReadyUserNotifications
                .stream()
                .map(notification -> new NotificationDto(
                        notification.getEventId(),
                        notification.getFieldsChange().getName(),
                        notification.getFieldsChange().getMaxPlaces(),
                        notification.getFieldsChange().getDate(),
                        notification.getFieldsChange().getCost(),
                        notification.getFieldsChange().getDuration(),
                        notification.getFieldsChange().getLocationId()
                )).toList();
        return new ResponseEntity<>(notificationsResponse, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Void> markNotificationAsRead(
            @RequestBody NotificationRequest notifications,
            @AuthenticationPrincipal CustomPrincipal customPrincipal
    ){
        notificationService.markAllNotificationRead(notifications, customPrincipal.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

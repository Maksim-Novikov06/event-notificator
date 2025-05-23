package com.paradise.eventnotificator.service;

import com.paradise.eventnotificator.domain.entity.EventFieldsChange;
import com.paradise.eventnotificator.domain.entity.Notification;
import com.paradise.eventnotificator.dto.EventChangeKafkaMessage;
import com.paradise.eventnotificator.dto.NotificationRequest;
import com.paradise.eventnotificator.repository.NotificationRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final static Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationRepository notificationRepository;




    public void save(EventChangeKafkaMessage value) {
        logger.info("An attempt to save a notification in the database");
        Notification notification = mapperToNotification(value);

        notification.setToFieldsChange();
        notificationRepository.save(notification);


    }

    private Notification mapperToNotification(EventChangeKafkaMessage value) {

        return new Notification(
                null,
                value.getEventId(),
                value.getModifierById(),
                value.getOwnerEventId(),
                new EventFieldsChange(
                        null,
                        null,
                        value.getName(),
                        value.getMaxPlaces(),
                        value.getDate(),
                        value.getCost(),
                        value.getDuration(),
                        value.getLocationId(),
                        value.getStatus()
                ),
                false,
                LocalDateTime.now(),
                new HashSet<>(value.getRegistrationsOnEvent())
        );
    }

    public List<Notification> getAllNotReadyUserNotifications(Long id) {
        return notificationRepository.findAllNotReadUserNotifications(id);
    }

    public void markAllNotificationRead(NotificationRequest notifications, Long id) {
        int res = notificationRepository.markNotificationAsRead(
                notifications.getNotificationIds(),
                id
        );

        if (res == 0) {
            logger.error("No notifications marked as read");
            throw new EntityNotFoundException("No notifications marked as read");

        }
    }

    public List<Long> deleteNotificationsByMoreDays(int days) {
        logger.info("Delete notifications by more days: {}", days);
        return notificationRepository.deleteNotificationByDate(days);
    }
}

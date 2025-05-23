package com.paradise.eventnotificator.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class SchedulerNotificationService {

    private static final Logger log = LoggerFactory.getLogger(SchedulerNotificationService.class);
    private final NotificationService notificationService;
    private static final int RETENTION_DAYS = 1;


    @Scheduled(cron = "0 */1 * * * *")
    public void removeOldNotification() {
        log.info("Scheduled delete notification started");
        List<Long> notificationIds= notificationService.deleteNotificationsByMoreDays(RETENTION_DAYS);
        log.info("Scheduled delete notification Ids={}", notificationIds);
    }
}
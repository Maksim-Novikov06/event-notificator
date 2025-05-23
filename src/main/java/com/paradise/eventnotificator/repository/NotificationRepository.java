package com.paradise.eventnotificator.repository;

import com.paradise.eventnotificator.domain.entity.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

    @Query("""
    SELECT DISTINCT en FROM Notification en
    WHERE :userId MEMBER OF en.registrations
    AND en.isRead=false
""")
    List<Notification> findAllNotReadUserNotifications(@Param("userId") Long id);

    @Transactional
    @Modifying
    @Query("""
    UPDATE Notification en SET en.isRead = true
    WHERE en.id in :notificationIds
    AND :userId MEMBER OF en.registrations
    AND en.isRead = false
""")
    int markNotificationAsRead(
            @Param("notificationIds") List<Long> notificationIds,
            @Param("userId") Long id);


    @Transactional
    @Modifying
    @Query(value = """
    DELETE FROM notifications en  WHERE en.created_at < CURRENT_TIMESTAMP - (INTERVAL '1 minute' * :days)
    RETURNING id
""", nativeQuery = true)
    List<Long> deleteNotificationByDate(@Param("days") int days);
}

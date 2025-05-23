package com.paradise.eventnotificator.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "modifier_id")
    private Long modifierById;

    @Column(name = "owner_event_id", nullable = false)
    private Long ownerEventId;

    @OneToOne(mappedBy = "notification", cascade = CascadeType.ALL)
    private EventFieldsChange fieldsChange;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(name = "notification_registrations", joinColumns = @JoinColumn(name = "notification_id"))
    private Set<Long> registrations = new HashSet<>();


    public void setToFieldsChange() {
        if (this.fieldsChange.getNotification() != this) {
            this.fieldsChange.setNotification(this);
        }
    }
}

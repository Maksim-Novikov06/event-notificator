package com.paradise.eventnotificator.domain.entity;

import com.paradise.eventnotificator.domain.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fields_changes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventFieldsChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "notification_id",  nullable = false)
    private Notification notification;

    @Column(name = "name")
    private String name;

    @Column(name = "max_places")
    private Integer maxPlaces;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "location_id")
    private Long locationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventStatus status;
}

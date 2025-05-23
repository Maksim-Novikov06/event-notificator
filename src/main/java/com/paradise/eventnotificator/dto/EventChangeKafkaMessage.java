package com.paradise.eventnotificator.dto;


import com.paradise.eventnotificator.domain.EventStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EventChangeKafkaMessage {

    private Long eventId;

    private Long modifierById;

    private Long ownerEventId;

    private String name;

    private Integer maxPlaces;

    private LocalDateTime date;

    private Integer cost;

    private Integer duration;

    private Long locationId;

    private EventStatus status;

    private List<Long> registrationsOnEvent;

}

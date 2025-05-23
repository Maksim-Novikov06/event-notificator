package com.paradise.eventnotificator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class NotificationDto {

    private Long eventId;

    private String name;

    private Integer maxPlaces;

    private LocalDateTime date;

    private Integer cost;

    private Integer duration;

    private Long locationId;


}

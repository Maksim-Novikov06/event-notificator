package com.paradise.eventnotificator.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotificationRequest {

    @Size(min = 1)
    List<Long> notificationIds;
}

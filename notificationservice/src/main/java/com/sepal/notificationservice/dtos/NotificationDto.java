package com.sepal.notificationservice.dtos;

import lombok.Data;

@Data
public class NotificationDto {
    String messageDetails;
    String status;
    UserDto userDto;

}

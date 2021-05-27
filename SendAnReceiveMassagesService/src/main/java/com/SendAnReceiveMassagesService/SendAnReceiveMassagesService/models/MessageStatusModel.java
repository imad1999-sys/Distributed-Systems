package com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageStatusModel {
    private MessageModel messageModel;
    private String status;

}

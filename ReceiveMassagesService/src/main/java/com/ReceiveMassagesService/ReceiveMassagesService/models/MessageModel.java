package com.ReceiveMassagesService.ReceiveMassagesService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageModel {
    private String message;
    private String nameOfSender;
}

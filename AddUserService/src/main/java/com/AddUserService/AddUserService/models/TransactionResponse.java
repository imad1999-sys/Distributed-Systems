package com.AddUserService.AddUserService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private UserModel userModel;
    private String name;
    private int transactionId;
    private String message;
}

package com.retailer.rewards.exceptions;

import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private LocalDateTime localDateTime;
    private String message;
    private String path;
    private String errorCode;

    public String getMessage()
    {
       return this.message ="CUSTOMER_ID_NOT_FOUND";
    }
}
package com.theduckfood.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleMessageResponse {
    private boolean error;
    private String message;
}

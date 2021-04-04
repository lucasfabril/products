package com.pedrone.products.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ArgumentNotValidException {
    private Integer status_code;
    private String message;
}

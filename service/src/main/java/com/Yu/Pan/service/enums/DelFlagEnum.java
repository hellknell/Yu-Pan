package com.Yu.Pan.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DelFlagEnum {
    YES(true),
    NO(false);
    private Boolean flag;
}

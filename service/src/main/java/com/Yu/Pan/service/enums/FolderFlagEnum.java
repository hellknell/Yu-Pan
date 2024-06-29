package com.Yu.Pan.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FolderFlagEnum {
    FALSE(false), TRUE(true);
    private Boolean flag;
}

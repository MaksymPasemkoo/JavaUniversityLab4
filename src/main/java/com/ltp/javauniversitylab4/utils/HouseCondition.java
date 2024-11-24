package com.ltp.javauniversitylab4.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public enum HouseCondition {
    ECONOMY("economy"),
    STANDARD("standard"),
    LUXURY("luxury"),
    PRESIDENT("president");

   private final String textCondition;

    HouseCondition(final String textCondition) {
        this.textCondition = textCondition;
    }


}

package com.example.citron.util;

import com.example.citron.domaine.enums.Season;

import java.time.LocalDate;

public class SaisonUtil {
    public static Season getSaisonFromDate(LocalDate date) {
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        if ((month == 3 && day >= 20) || month == 4 || month == 5 || (month == 6 && day <= 20)) {
            return Season.SPRING;
        } else if ((month == 6 && day >= 21) || month == 7 || month == 8 || (month == 9 && day <= 22)) {
            return Season.SUMMER;
        } else if ((month == 9 && day >= 23) || month == 10 || month == 11 || (month == 12 && day <= 20)) {
            return Season.FALL;
        } else {
            return Season.WINTER;
        }
    }
}
package com.example.devedbaseproject.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tools {
    public static String getLocalDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(formatter);
    }
}

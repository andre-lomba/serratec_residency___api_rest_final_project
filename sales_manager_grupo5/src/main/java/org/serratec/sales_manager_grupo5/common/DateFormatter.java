package org.serratec.sales_manager_grupo5.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static final String dateFormat(LocalDateTime date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            String dateString = date.format(formatter);
            return dateString;
        } catch (Exception e) {
            throw new RuntimeException("Data inválida.");
        }
    }

}

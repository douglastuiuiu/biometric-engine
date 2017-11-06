package br.com.douglastuiuiu.biometricengine.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author douglas
 * @since 21/03/2017
 */
public class DateUtil {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public static String localDateToString(LocalDate localDate, String mask) throws Exception {
        try {
            if (mask == null) {
                throw new Exception("Máscara não informada!");
            }
            if (localDate != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(mask);
                return formatter.format(localDate);
            } else {
                throw new Exception("Data não informada!");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, String mask) throws Exception {
        try {
            if (mask == null) {
                throw new Exception("Máscara não informada!");
            }
            if (localDateTime != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(mask);
                return formatter.format(localDateTime);
            } else {
                throw new Exception("Datahora não informada!");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public static LocalDate stringToLocalDate(String date, String mask) throws Exception {
        try {
            if (mask == null) {
                throw new Exception("Máscara não informada!");
            }
            if (date != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(mask);
                return LocalDate.parse(date, formatter);
            } else {
                throw new Exception("Data não informada!");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }
}

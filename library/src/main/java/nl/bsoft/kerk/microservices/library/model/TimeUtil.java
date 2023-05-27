package nl.bsoft.kerk.microservices.library.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static ZonedDateTime getDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.S[S[S]]]XXX");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        ZoneId zoneId = ZoneId.of("UTC");
        ZoneOffset offset = zoneId.getRules().getOffset(dateTime);

        return ZonedDateTime.of(dateTime, offset);
    }

}

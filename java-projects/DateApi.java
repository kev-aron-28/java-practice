
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateApi {
  public static void main(String[] args) {
    LocalDate date = LocalDate.of(2023, 3, 18);

    LocalDateTime nowDate = LocalDateTime.of(2023, Month.APRIL, 24, 13, 00);

    ZonedDateTime flightDepTimeZ = nowDate.atZone(ZoneId.of("Europe/Dublin"));

    
    
  }
}

# Dates in java
Java has two main date/time worlds
1. The legacy API
2. The modern java.time API (Java +8)



# Modern API

``` java
import java.time
```

## Concepts
| Concept            | Example                |
| ------------------ | ---------------------- |
| Date only          | 2026-02-28             |
| Time only          | 13:45:22               |
| Date + Time        | 2026-02-28T13:45       |
| Date + Time + Zone | 2026-02-28T13:45-06:00 |
| Instant            | Machine timestamp      |
| Duration           | 5 hours                |
| Period             | 3 months               |

- LocalDate: date without a time or a timezone
``` java
LocalDate date = LocalDate.now();
LocalDate custom = LocalDate.of(2026, 2, 28);
```

- LocalTime: Time without date or timezone
``` java
LocalTime time = LocalTime.now();
```

- LocalDateTime: Date + time but no zone
``` java
LocalDateTime now = LocalDateTime.now();
```

- Instant: A moment in UTC (machine time)
``` java
Instant instant = Instant.now();
``` 
It stores secons _ nanosecons since epoch

- ZonedDateTime: Date + time + timezone
``` java
ZonedDateTime zdt = ZonedDateTime.now();
```

- OffsetDateTime: Date + Time + offset

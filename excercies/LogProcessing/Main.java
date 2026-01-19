
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
class LogEntry {
        private LocalDateTime timestamp;
        private String serviceName;
        private String type;
        private String message;
        private Integer duration;

        public LogEntry(LocalDateTime timestamp, String serviceName, String type, String message, Integer duration) {
            this.timestamp = timestamp;
            this.serviceName = serviceName;
            this.type = type;
            this.message = message;
            this.duration = duration;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setduration(Integer duration) {
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "LogEntry [timestamp=" + timestamp + ", serviceName=" + serviceName + ", type=" + type + ", message="
                    + message + ", duration=" + duration + "]";
        }    

        
    }

public class Main {    
    public static void main(String[] args) {
        try (
            BufferedReader bf = Files.newBufferedReader(Path.of("application.log"), StandardCharsets.UTF_8)
        ) {
            System.out.println("HELLO WORLD");
            String line;
            List<LogEntry> entries = new ArrayList<>();
            
            while ((line = bf.readLine()) != null) { 
                String [] parts = line.split("\\|");

                if(parts.length != 5) continue;

                if(parts[1].length() < 1) continue;

                LocalDateTime date;
                try {
                    date = LocalDateTime.parse(parts[0]);
                } catch (DateTimeParseException e) {
                    continue;
                }


                String serviceName = parts[1];

                if(parts[2].length() < 1) continue;

                String type = parts[2];

                if(parts[3].length() < 2) continue;

                String message = parts[3];

                Integer status;
                try {
                    if(parts[4].length() < 1) continue;
                    status = Integer.parseInt(parts[4]);
                    
                } catch (NumberFormatException e) {
                    continue;
                }



                // AFter validation we create or object
                LogEntry entry = new LogEntry(date, serviceName, type, message, status);
                entries.add(entry);
            }

            Map<String, List<LogEntry>> byService = entries.stream().collect(
                    Collectors.groupingBy(LogEntry::getServiceName)
                );

                byService.forEach((service, logs) -> {
                    double avgDuration = logs.stream().collect(Collectors.averagingInt(e -> e.getDuration()));

                    long countError = logs.stream().filter(e -> e.getType().equals("ERROR")).count();

                    System.out.println("SERVICE: " + service);
                    System.out.println("AVG DURATION: " + avgDuration);
                    System.out.println("ERRORS: " + countError);
                });
            
                System.out.println("Top 2 lowest: ");
                entries.stream().sorted(Comparator.comparingInt((LogEntry e) -> e.getDuration()).reversed())
                .limit(2)
                .forEach(e -> System.out.println(" " + e));
                ;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
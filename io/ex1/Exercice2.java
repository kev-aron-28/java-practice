package ex1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Exercice2 {

    enum TransactionType {
        SALE,
        REFUND
    }

    record Transaction (
        LocalDate date,
        TransactionType type,
        Long userId,
        BigDecimal amount
    ) {}

    class TransactionParser {
        public static Transaction parse(String line) {
            String parts[] = line.split(",");

            if(parts.length != 4) {
                throw new IllegalArgumentException("Invalid format");
            }

            return new Transaction(
                LocalDate.parse(parts[0]), 
                TransactionType.valueOf(parts[1]),
                Long.valueOf(parts[2]), 
                new BigDecimal(parts[3]));
        } 
    }
    
    class ErrorLogger implements Closeable {

        private final BufferedWriter writer;


        public ErrorLogger(Path output) throws IOException {
            writer = Files.newBufferedWriter(output);
        }

        public void log(String file, long line, String error) throws IOException {
            writer.write(
                file + " line " + line + " -> " + error
            );

            writer.newLine();
        }

        @Override
        public void close() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
 
    public static void main(String[] args) {
        Path dir = Path.of("./data");

        List<Path> filesToSearch = new ArrayList<>();

        try (Stream<Path> files = Files.list(dir)) {
            filesToSearch = files
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".csv"))
                .toList();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(filesToSearch);

        for(Path path : filesToSearch) {
            System.out.println("FILE " + path.getFileName());
            try (BufferedReader br = Files.newBufferedReader(path) ) {
                String line;
                while ((line = br.readLine()) != null) { 
                    Transaction tx = TransactionParser.parse(line);


                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }


    }
}

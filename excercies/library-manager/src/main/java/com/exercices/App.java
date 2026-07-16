package com.exercices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Path path = Path.of("./test.dat");

        if(!Files.exists(path)) {
            Files.createFile(path);
        }

        BookFIleRepository repo = new BookFIleRepository(path);

        Book book = new Book(
            1L,
            "Test",
            "test 1",
            "19kld90",
            "test",
            LocalDate.of(2023, 1, 23),
            12L,
            12L
        );


        Book book2 = new Book(
            2L,
            "Test",
            "test 1",
            "19kld90",
            "test",
            LocalDate.of(2023, 1, 23),
            12L,
            12L
        );

        repo.save(book);
        repo.save(book2);

        repo.findById(1L);
    }
}

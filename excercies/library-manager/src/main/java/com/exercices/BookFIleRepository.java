package com.exercices;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.Optional;

public class BookFIleRepository {
    private final Path databasefile;
    public BookFIleRepository(Path databaseFile) {
        this.databasefile = databaseFile;
    }

    public void save(Book book) {
        try (ObjectOutputStream ob = new ObjectOutputStream(
            new BufferedOutputStream(
                new FileOutputStream(databasefile.toFile(), true)
            )
        )) {
            ob.writeObject(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Book> findById(Long id) {
        
        try (ObjectInputStream oi = new ObjectInputStream(
            new BufferedInputStream(
                new FileInputStream(databasefile.toFile())
            )
        )) {
            while (true) { 
                Book book = (Book) oi.readObject();

                System.out.println(book);
            }
        }
        catch (EOFException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}

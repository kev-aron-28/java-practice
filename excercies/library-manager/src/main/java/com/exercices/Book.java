package com.exercices;

import java.io.Serializable;
import java.time.LocalDate;

public record Book (
        Long id,
        String name,
        String author,
        String ISBN,
        String category,
        LocalDate publicationYear,
        Long totalCopies,
        Long availableCopies
    ) implements Serializable {}

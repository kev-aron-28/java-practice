package com.assessment.ticket_manager.DTO;

import org.hibernate.validator.constraints.Length;

import com.assessment.ticket_manager.Entities.Priority;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateTicketDTO(
    @NotEmpty
    @Length(min = 3)
    String title,
    @NotEmpty
    @Length(min = 3)
    String description,
    @NotNull
    Priority priority
) {
}
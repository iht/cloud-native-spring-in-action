package com.polarbookshop.catalogservice.domain;

import com.google.auto.value.AutoBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "The book ISBN must be defined.")
                @Pattern(
                        regexp = "^([0-9]{10}|[0-9]{13})$",
                        message = "The ISBN format must be valid.")
                String isbn,
        @NotBlank(message = "The book title must be defined.") String title,
        @NotBlank(message = "The book author must be defined.") String author,
        @NotNull(message = "The book price must be defined.") @Positive(message = "The book price must be greater than zero.") Double price) {

    public static Builder builder() {
        return new AutoBuilder_Book_Builder();
    }

    @AutoBuilder
    public interface Builder {
        Book build();

        Builder isbn(String isbn);

        Builder title(String title);

        Builder author(String author);

        Builder price(Double price);
    }
}

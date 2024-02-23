package com.polarbookshop.catalogservice.domain;

import com.google.auto.value.AutoBuilder;

public record Book(String isbn, String title, String author, Double price) {

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

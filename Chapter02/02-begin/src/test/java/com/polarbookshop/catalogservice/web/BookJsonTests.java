package com.polarbookshop.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

@JsonTest
public class BookJsonTests {
    @Autowired private JacksonTester<Book> json;

    @Test
    void testSerialize() throws IOException {
        Book book =
                Book.builder()
                        .isbn("1234567890")
                        .title("Title")
                        .author("Author")
                        .price(9.98)
                        .build();
        JsonContent<Book> content = json.write(book);
        assertThat(content).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(content).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(content).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(content).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws IOException {
        String content =
                "{\"isbn\":\"1234567890\",\"title\":\"Title\",\"author\":\"Author\",\"price\":9.98}";
        Book book = json.parse(content).getObject();
        assertThat(book)
                .isEqualTo(
                        Book.builder()
                                .isbn("1234567890")
                                .title("Title")
                                .author("Author")
                                .price(9.98)
                                .build());
    }
}

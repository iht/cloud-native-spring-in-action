package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

    @Autowired private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenBookCreated() {
        Book expectedBook =
                Book.builder()
                        .isbn("1231231231")
                        .title("Title")
                        .author("Author")
                        .price(9.90)
                        .build();

        webTestClient
                .post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Book.class)
                .value(
                        b -> {
                            assertThat(b).isNotNull();
                            assertThat(b.isbn()).isEqualTo(expectedBook.isbn());
                        });
    }
}

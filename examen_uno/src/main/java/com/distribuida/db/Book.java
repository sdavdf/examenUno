package com.distribuida.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="book")
@Data
public class Book {
    @Id
    private Integer id;

    private String isbn;
    private String title;
    private String author;
    private Integer price;
}

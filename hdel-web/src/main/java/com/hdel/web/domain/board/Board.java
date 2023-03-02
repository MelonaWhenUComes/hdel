package com.hdel.web.domain.board;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="ID")
    private Long id;

    @Column(length = 500, nullable = true, name="TITLE")
    private String title;
    @Column(nullable = true, name="CONTENTS")
    private String contents;
    @Column(length = 100, nullable = true, name="AUTHOR")
    private String author;

    @Column(nullable = true, name="CREATED_AT")
    private LocalDateTime createdAt;

    @Builder
    public Board(String title, String contents, String author, LocalDateTime createdAt) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.createdAt = createdAt;
    }

    public void updateBoard(String title, String contents, String author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
    }
}

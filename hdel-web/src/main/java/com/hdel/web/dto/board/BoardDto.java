package com.hdel.web.dto.board;


import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BoardDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    //createdAt 은 String
    private String createdAt;
}

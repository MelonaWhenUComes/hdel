package com.hdel.web.controller.board;

import com.hdel.web.domain.board.Board;
import com.hdel.web.dto.board.BoardDto;
import com.hdel.web.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
@CrossOrigin
@RequiredArgsConstructor
public class BoardController {
    public final BoardService boardService;

    @GetMapping(path = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<BoardDto> boardList() {
        BoardDto boardDto = new BoardDto();
        List<BoardDto> boardDtoList = new ArrayList<>();

        boardDto.setAuthor("장영춘");
        boardDto.setTitle("제목");

        boardDtoList.add(boardDto);

        boardDto = new BoardDto();
        boardDto.setAuthor("장지우");
        boardDto.setTitle("타이틀 ");

        boardDtoList.add(boardDto);

        return boardDtoList;
    }

    @GetMapping("/board/{id}")
    public BoardDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping("/board")
    public Board create(@RequestBody BoardDto boardDto) {
        return boardService.create(boardDto);
    }

    @PostMapping("/board")
    public Board update(@RequestBody BoardDto boardDto) {
        return boardService.update(boardDto);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }
}




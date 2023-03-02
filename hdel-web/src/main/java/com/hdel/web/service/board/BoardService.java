package com.hdel.web.service.board;

import com.hdel.web.domain.board.Board;
import com.hdel.web.domain.board.BoardRepository;
import com.hdel.web.dto.board.BoardDto;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import model.Header;
import model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    /**
     * 게시글 목록 가져오기
     */
    /*public List<BoardDto> getBoardList() {
        List<Board> boardEntities = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();

        for (Board board : boardEntities) {
            BoardDto dto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();

            dtos.add(dto);
        }

        return dtos;
    }*/
    public Header<List<BoardDto>> getBoardList(Pageable pageable) {
        List<BoardDto> dtos = new ArrayList<>();

        Page<Board> boardPage = boardRepository.findAllByOrderByIdDesc(pageable);
        for (Board board : boardPage) {
            BoardDto dto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();

            dtos.add(dto);
        }

        Pagination pagination = new Pagination(
                (int) boardPage.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(dtos, pagination);
    }

    /**
     * 게시글 가져오기
     */
    public BoardDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .author(board.getAuthor())
                .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
    }

    /**
     * 게시글 등록
     */
    public Board create(BoardDto boardDto) {
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .author(boardDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .build();
        return boardRepository.save(board);
    }

    /**
     * 게시글 수정
     */
    public Board update(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        board.updateBoard(boardDto.getTitle()
                , boardDto.getContents()
                , boardDto.getAuthor()
        );

        return boardRepository.save(board);
    }

    /**
     * 게시글 삭제
     */
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(board);
    }
}

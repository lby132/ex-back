package com.example.back.exback.api.service.noticeboard;

import com.example.back.exback.annotation.Trace;
import com.example.back.exback.api.controller.noticeboard.request.BoardRequest;
import com.example.back.exback.api.controller.noticeboard.response.BoardResponse;
import com.example.back.exback.api.exception.PostNotFound;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import com.example.back.exback.domain.noticeboard.board.Board;
import com.example.back.exback.domain.noticeboard.board.BoardEditor;
import com.example.back.exback.domain.noticeboard.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Long registrationBoard(Long id, BoardRequest request) {
        Member findMember = memberRepository.findById(id).orElseThrow(PostNotFound::new);
        BoardCommon boardCommon = new BoardCommon(request.getWriter(), request.getContent());
        Board board = Board.registBoard(findMember, request.getTitle(), boardCommon);

        boardRepository.save(board);

        return board.getId();
    }

    @Transactional(readOnly = true)
    @Trace //aop 연습용 어노테이션
    public BoardResponse getBoardOne(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        return BoardResponse.of(board);
    }

    public void edit(Long id, BoardEditor editor) {
        Board board = boardRepository.findById(id).orElseThrow(PostNotFound::new);
        board.updateBoard(editor);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getBoardAll() {
        log.info("BoardService.getBoardAll");
        return boardRepository.findAll().stream()
                .map(BoardResponse::of)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(PostNotFound::new);
        board.deleteBoard();
    }
}

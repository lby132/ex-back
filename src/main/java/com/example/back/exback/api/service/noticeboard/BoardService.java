package com.example.back.exback.api.service.noticeboard;

import com.example.back.exback.api.controller.noticeboard.request.BoardRequest;
import com.example.back.exback.api.controller.noticeboard.response.BoardResponse;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import com.example.back.exback.domain.noticeboard.board.Board;
import com.example.back.exback.domain.noticeboard.board.BoardEditor;
import com.example.back.exback.domain.noticeboard.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Long registrationBoard(Long id, BoardRequest request) {
        Member findMember = memberRepository.findById(id).orElseThrow(NoSuchElementException::new);
        BoardCommon boardCommon = new BoardCommon(request.getWriter(), request.getContent());
        Board board = Board.registBoard(findMember, request.getTitle(), boardCommon);

        boardRepository.save(board);

        return board.getId();
    }

    public void edit(Long id, BoardEditor editor) {
        Board board = boardRepository.findById(id).orElseThrow(NoSuchElementException::new);
        board.updateBoard(editor);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getBoardAll() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::of)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NoSuchElementException::new);
        board.deleteBoard();
    }
}

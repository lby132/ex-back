package com.example.back.exback.api.controller.noticeboard;

import com.example.back.exback.api.ApiResponse;
import com.example.back.exback.api.controller.noticeboard.request.BoardRequest;
import com.example.back.exback.api.controller.noticeboard.response.BoardResponse;
import com.example.back.exback.api.service.noticeboard.BoardService;
import com.example.back.exback.domain.noticeboard.board.Board;
import com.example.back.exback.domain.noticeboard.board.BoardEditor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/v1/list")
    public ApiResponse<List<BoardResponse>> boardList() {
        return ApiResponse.ok(boardService.getBoardAll());
    }

    @PostMapping("/v1/registration/{memberId}")
    public void registrationBoard(@PathVariable("memberId") Long memberId, @RequestBody BoardRequest request) {
        boardService.registrationBoard(memberId, request);
    }

    @PostMapping("/v1/edit/{boardId}")
    public void editBoard(@PathVariable("boardId") Long boardId, @RequestBody BoardEditor editor) {
        boardService.edit(boardId, editor);
    }

    @DeleteMapping("/v1/delete/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId, @RequestBody BoardEditor editor) {
        boardService.edit(boardId, editor);
    }
}

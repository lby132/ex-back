package com.example.back.exback.api.controller.noticeboard;

import com.example.back.exback.api.ApiResponse;
import com.example.back.exback.api.controller.noticeboard.request.BoardRequest;
import com.example.back.exback.api.controller.noticeboard.response.BoardResponse;
import com.example.back.exback.api.service.noticeboard.BoardService;
import com.example.back.exback.domain.noticeboard.board.BoardEditor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/v1/list")
    public ApiResponse<List<BoardResponse>> boardList() {
        return ApiResponse.ok(boardService.getBoardAll());
    }

    @GetMapping("/v1/{boardId}")
    public ApiResponse<BoardResponse> getBoardOne(@PathVariable("boardId") Long boardId) {
        return ApiResponse.ok(boardService.getBoardOne(boardId));
    }

    @PostMapping("/v1/registration/{memberId}")
    public void registrationBoard(@PathVariable("memberId") Long memberId, @RequestBody @Valid BoardRequest request) {
        boardService.registrationBoard(memberId, request);
    }

    @PostMapping("/v1/edit/{boardId}")
    public void editBoard(@PathVariable("boardId") Long boardId, @RequestBody BoardEditor editor) {
        boardService.edit(boardId, editor);
    }

    @DeleteMapping("/v1/delete/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId) {
        boardService.delete(boardId);
    }
}

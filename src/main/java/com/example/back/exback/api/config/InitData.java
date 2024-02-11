package com.example.back.exback.api.config;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import com.example.back.exback.domain.noticeboard.board.Board;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.boardData();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void boardData() {
            JoinRequest request = JoinRequest.builder()
                    .zipcode("06222")
                    .address("서울시")
                    .addressDetail("27-5번지")
                    .userId("memberA")
                    .userPw("1234")
                    .age(24)
                    .phone("010-9990-1123")
                    .gender('M')
                    .build();
            Member newMember = Member.createMember(request);

            em.persist(newMember);


            for (int i = 0; i < 20; i++) {
                BoardCommon boardCommon = new BoardCommon("memberA", "게시판테스트" + i);
                em.persist(Board.registBoard(newMember, "board" + i, boardCommon));
            }
        }
    }
}

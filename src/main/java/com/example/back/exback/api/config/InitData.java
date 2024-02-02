package com.example.back.exback.api.config;

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
            Address address = new Address("06222", "서울시", "27-5번지");
            Member newMember = Member.createMember(address, "memberA", "1234", "010-9990-1123", 'M');
            em.persist(newMember);


            for (int i = 0; i < 20; i++) {
                BoardCommon boardCommon = new BoardCommon("memberA", "게시판테스트" + i);
                em.persist(Board.registBoard(newMember, "board" + i, boardCommon));
            }
        }
    }
}

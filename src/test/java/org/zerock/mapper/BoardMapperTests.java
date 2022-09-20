package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@Log4j
public class BoardMapperTests {
    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList() {
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();
        board.setTitle("new_test_title");
        board.setContent("new_test_content");
        board.setWriter("newbie");

        mapper.insert(board);

        log.info(board);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO board = new BoardVO();
        board.setTitle("new_test_title select key");
        board.setContent("new_test_content select key");
        board.setWriter("newbie");

        mapper.insertSelectKey(board);

        log.info(board);
    }

    @Test
    public void testRead() {
        // 존재하는 게시물 번호로 테스트
        BoardVO board = mapper.read(2L);

        log.info(board);
    }

    @Test
    public void testDelete() {
        log.info("DELETE COUNT : " + mapper.delete(3L));
    }

    @Test
    public void testUpdate(){
        BoardVO board = new BoardVO();
        // 실행전 존재하는 번호인지 확인할 것
        board.setBno(2L);
        board.setTitle("update_title");
        board.setContent("update_content");
        board.setWriter("user00");

        int count = mapper.update(board);

        log.info("UPDATE COUNT : " + count);
    }

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();

        // 10개씩 3페이지
        cri.setPageNum(3);
        cri.setAmount(10);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        // list.forEach(board -> log.info(board));

        list.forEach(board -> log.info(board.getBno()));
    }

}

package org.zerock.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
// Test for Controller
@WebAppConfiguration    // ServletContext를 이용, WebApplicationContext 사용하기 위해
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@Log4j
public class BoardControllerTests {

    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;

    // MockMvc : 가짜 MVC, 가짜로 URL과 파라미터 등을 브라우저에서 사용하는 것처럼 만들어서 Controller를 실행할 수 있음
    private MockMvc mockMvc;

    // @Before : 모든 테스트 전에 매번 실행되는 메서드
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    // MockMvcRequestBuilders를 이용해서 GET방식의 호출
    @Test
    public void testList() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                        .andReturn().getModelAndView().getModelMap()
        );
    }

    // MockMvcRequestBuilders를 이용해서 POST방식으로 데이터 전달
    @Test
    public void testRegister() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title", "test_new_title")
                .param("content", "test_new_content")
                .param("writer", "user001")).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testGet()  throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders
                .get("/board/get").param("bno", "1"))
                .andReturn().getModelAndView().getModelMap());
    }

    @Test
    public void testModify() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "1")
                .param("title", "Modify_Test_New_title")
                .param("content", "Modify_Test_New_Content")
                .param("writer", "user00"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testRemove() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("bno", "7"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }
}

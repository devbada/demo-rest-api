package me.minam.demorestapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.ModuleTree.ModuleKind;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 이 클래스는 단위테스트라고 보기 어렵다.
 * WebMvcTest = 슬라이싱 테스트
 */

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc; // 스프링 MVC 테스트 핵심 클래스 - 컨트롤러 테스트용으로 많이 쓰임

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @Test
    @SneakyThrows
    public void createEvent() {
        Event event = Event.builder()
                .name("minamTest")
                .description("REST API Development with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2020, Month.NOVEMBER, 22, 00, 00))
                .beginEventDateTime(LocalDateTime.of(2021,Month.JANUARY, 01, 00, 00))
                .maxPrice(10000)
                .basePrice(100)
                .limitOfEnrollment(100)
                .location("선릉역 10번출구")
                .build();

        event.setId(10);

        Event save = eventRepository.save(event);

        Mockito.doReturn(save).when(this.eventRepository).save(event);

        Mockito.when(event).thenReturn(event);

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(event))
        )
        //.andExpect(status().isOk())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("id").exists())
        .andDo(print());
    }
}

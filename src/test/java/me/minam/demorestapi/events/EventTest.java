package me.minam.demorestapi.events;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("minam Spring Inflearn Study")
                .description("REST API development with Spring")
                .build();

        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        Event event = new Event();
        event.setName("minam");
        event.setDescription("JavaBean is Okay");

        assertThat(event.getName()).isEqualTo("minam");
        assertThat(event.getDescription()).isNotEmpty();

    }
}
package com.exampleView.controllers;


import com.exampleLogic.dto.OwnerDTO;
import com.exampleLogic.utils.TruncateTable;
import com.exampleView.services.OwnerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OwnerService ownerService; // Сервис, который используется в контроллере

    @InjectMocks
    private OwnerController ownerController;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        TruncateTable truncateTable = new TruncateTable();
        truncateTable.truncate("owners");
        truncateTable.truncate("cats");
        truncateTable.truncate("catsfriends");
    }

    @Test
    public void createOwner() throws Exception {
        OwnerDTO ownerDTO = new OwnerDTO.Builder()
                .id(1l)
                .name("alex")
                .dateBirth(LocalDate.of(2000, 12, 15))
                .catsId(new HashSet<>())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String ownerJson = objectMapper.writeValueAsString(ownerDTO);

        mockMvc.perform(post("/owners/create") // Изменяем на POST запрос к /create
                        .contentType(MediaType.APPLICATION_JSON) // Указываем тип контента
                        .content(ownerJson)) // Передаем JSON в теле запроса
                .andExpect(status().isOk()) // Проверяем статус ответа
                .andExpect(content().string("Владелец сохранен")); // Проверяем содержимое ответа

    }

}

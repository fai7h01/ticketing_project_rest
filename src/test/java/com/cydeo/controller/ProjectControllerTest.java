package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.enums.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;

    static UserDTO manager;
    static ProjectDTO project;

    @BeforeAll
    static void setUp(){

        manager = new UserDTO(
                2L,
                "",
                "",
                "Luka",
                "abc1",
                "",
                true,
                "",
                new RoleDTO(2L, "Manager"),
                Gender.MALE);

        project = new ProjectDTO(
                "API Project",
                "PR001",
                manager,
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                "some details",
                Status.OPEN
        );
    }

    @Test
    void givenNoToken_getProjects() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/project"))
                .andExpect(status().is4xxClientError());
    }

}

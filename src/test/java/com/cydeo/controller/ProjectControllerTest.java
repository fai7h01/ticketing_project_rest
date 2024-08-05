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
import org.springframework.http.MediaType;
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

    static String token;

    static UserDTO manager;
    static ProjectDTO project;

    @BeforeAll
    static void setUp(){

        token = "Bearer " +  "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI3cWVHRTBadGJDRjlmdW8wZGxPcV81SnFxT1RBcGVleEtXZEptQXgtdjFBIn0.eyJleHAiOjE3MjI4ODE2OTEsImlhdCI6MTcyMjg4MTM5MSwianRpIjoiMzMwOWRlM2YtYjJhZi00MmNkLTgyMjQtMmJlMDRhYzE3NDlkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2N5ZGVvLWRldiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJiMjAxZTI3Zi0yNmVhLTRmMDAtOGZiMi0yY2FjM2ZjYWVjZDQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ0aWNrZXRpbmctYXBwIiwic2Vzc2lvbl9zdGF0ZSI6ImU5MDZkYmIzLTFkNmItNDQ5NS04NWVhLTllNmFhNmU5MmE2ZCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgxIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtY3lkZW8tZGV2IiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJ0aWNrZXRpbmctYXBwIjp7InJvbGVzIjpbIk1hbmFnZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiJlOTA2ZGJiMy0xZDZiLTQ0OTUtODVlYS05ZTZhYTZlOTJhNmQiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoibHVrYSJ9.g93WDYwxplMM5JicvkVnn40UwW20rvDJzKmlpZ5bIaanJh9ednjm8XJpeaULbppXVbPjdLxMHFqQP9_R5IvajX4VKsZ498i0H0Hzw5LkqtdvvKc3TaRG_iC-iG9-YxK029xuoP4qCkayjcENxyNDa9cqVUQbPdCAdIhKlKRR-ajhaKMkpaoGzNLQIVT6PT7Pqv6cTzVYZ5IcIj87lpGnuL5qonZxahwiPs64Blgn70bnvRdDa0wXfyoMJU0gCe-jeieNTuOEG9CrWNiLYs9iVv6NQb9iBJDRKY31wE9bQSGEiX7z4qbqZJPJZOOn_Tago-fxhZlW1cET4zlTmpgndA";

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

    @Test
    void givenToken_getProjects() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/project")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}

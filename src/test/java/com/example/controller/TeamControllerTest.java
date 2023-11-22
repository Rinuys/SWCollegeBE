package com.example.controller;

import com.example.dto.TeamRequest;
import com.example.model.Team;
import com.example.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)
public class TeamControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeamService teamService;

    private Team expected;

    @BeforeEach
    void setup(){
        this.expected = new Team();
        this.expected.setId(123L);
        this.expected.setName("test");
        this.expected.setMascot("mascot");
        this.expected.setLocation("test_location");
        this.expected.setFoundedDate("123123");
    }


    @Test
    void create() throws Exception {
        when(this.teamService.addTeam(any(TeamRequest.class)))
                .then((i) -> {
                    TeamRequest request = i.getArgument(0, TeamRequest.class);
                    return new Team(this.expected.getId()
                            , request.getName()
                            , request.getMascot()
                            , request.getLocation()
                            , request.getFoundedDate()
                            , null);
                });

        TeamRequest request = new TeamRequest();
        request.setName(this.expected.getName());
        request.setLocation(this.expected.getLocation());
        request.setMascot(this.expected.getMascot());
        request.setFoundedDate(this.expected.getFoundedDate());

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        mvc.perform(post("/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.mascot").value(expected.getMascot()))
                .andExpect(jsonPath("$.location").value(expected.getLocation()));

    }



}

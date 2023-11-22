package com.example.services;

import com.example.dto.TeamRequest;
import com.example.model.Team;
import com.example.repository.TeamRepository;
import com.example.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class TeamServiceTest {

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Test
    public void addTeamTest(){
        when(this.teamRepository.save(any(Team.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TeamRequest request = new TeamRequest();
        request.setName("hoho");
        request.setLocation("seoul");
        request.setMascot("asdf");
        request.setFoundedDate("1234:12:34");

        Team actual = this.teamService.addTeam(request);

        assertEquals(1L, actual.getId());
        assertEquals("hoho", actual.getName());
    }

    @Test
    public void searchByIdTest(){

    }
}

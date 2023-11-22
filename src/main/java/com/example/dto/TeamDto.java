package com.example.dto;

import com.example.model.Member;
import com.example.model.Team;
import com.example.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private Long id;
    private String name;
    private List<Member> memberList;

    public static TeamDto of(Long id, String Name, List<Member> memberList){
        return new TeamDto(id, Name, memberList);
    }

    public static TeamDto from(Team team){
        return new TeamDto(
                team.getId(),
                team.getName(),
                team.getMembers()
        );
    }
}

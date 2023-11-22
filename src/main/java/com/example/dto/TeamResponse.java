package com.example.dto;

import com.example.model.Member;
import com.example.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {
    private Long id;
    private String name;
    private String mascot;
    private String location;
    private String foundedDate;
    private List<Member> members;

    public static TeamResponse of(Long id, String name, String mascot, String location, String foundedDate, List<Member> members){
        return new TeamResponse(id, name, mascot, location, foundedDate, members);
    }

    public static TeamResponse from(Team team){
        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getMascot(),
                team.getLocation(),
                team.getFoundedDate(),
                team.getMembers()
        );
    }
//    public TeamResponse(Team team){
//        this.id = team.getId();
//        this.name = team.getName();
//        this.location = team.getLocation();
//        this.mascot = team.getMascot();
//        this.members = team.getMembers();
//    }
}

package com.example.dto;

import com.example.model.Member;
import com.example.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String joinedDate;
    private Long teamId;

    public static MemberResponse of(Long id, String firstName, String lastName, String address, String joinedDate, Long teamId){
        return new MemberResponse(id, firstName, lastName, address, joinedDate, teamId);
    }

    public static MemberResponse from(Member member){
        return new MemberResponse(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getAddress(),
                member.getJoinedDate(),
                member.getTeam().getId()
        );
    }
//
//    public MemberResponse(Member member){
//        this.id = member.getId();
//        this.firstName = member.getFirstName();
//        this.lastName = member.getLastName();
//        this.address = member.getAddress();
//        this.joinedDate = member.getJoinedDate();
//        this.teamId = member.getTeam().getId();
//    }
}

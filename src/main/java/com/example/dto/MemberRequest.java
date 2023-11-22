package com.example.dto;

import com.example.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String joinedDate;
    private Long teamId;
}

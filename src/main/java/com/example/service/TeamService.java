package com.example.service;

import com.example.dto.TeamDto;
import com.example.dto.TeamRequest;
import com.example.dto.TeamResponse;
import com.example.model.Member;
import com.example.model.Team;
import com.example.model.constant.TeamSearchType;
import com.example.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    // id 기반 team 개별 조회
    public Team searchById(Long id){
        return this.teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // 페이징 기반 Team 조회
    @Transactional(readOnly = true)
    public Page<TeamResponse> searchTeamByFilter(TeamSearchType searchType, String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return teamRepository.findAll(pageable).map(TeamResponse::from);
        }
        switch (searchType){
            case NAME:
                return teamRepository.findByName(searchKeyword, pageable).map(TeamResponse::from);
            case MASCOT:
                return teamRepository.findByMascot(searchKeyword, pageable).map(TeamResponse::from);
            case LOCATION:
                return teamRepository.findByLocation(searchKeyword, pageable).map(TeamResponse::from);
            default:
                return teamRepository.findAll(pageable).map(TeamResponse::from);
        }
    }

    // Team 등록
    public Team addTeam(TeamRequest teamRequest){
        Team newTeam = new Team();
        newTeam.setName(teamRequest.getName());
        newTeam.setLocation(teamRequest.getLocation());
        newTeam.setFoundedDate(teamRequest.getFoundedDate());
        newTeam.setMascot(teamRequest.getMascot());
        newTeam.setMembers(new ArrayList<>());

        return this.teamRepository.save(newTeam);
    }

    // Team 개별 수정
    public Team updateById(Long id, TeamRequest teamRequest){
        Team team = searchById(id);

        if(teamRequest.getName() != null){
            team.setName(teamRequest.getName());
        }
        if(teamRequest.getMascot() != null){
            team.setName(teamRequest.getMascot());
        }
        if(teamRequest.getLocation() != null){
            team.setLocation(teamRequest.getLocation());
        }
        if(teamRequest.getFoundedDate() != null){
            team.setFoundedDate(teamRequest.getFoundedDate());
        }

        return this.teamRepository.save(team);
    }

    // Team 전체 삭제
    public void deleteAll(){
        this.teamRepository.deleteAll();
    }

    // Team 개별 삭제
    public void deleteById(Long id){
        this.teamRepository.deleteById(id);
    }
}

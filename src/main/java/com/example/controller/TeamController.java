package com.example.controller;

import com.example.dto.TeamRequest;
import com.example.dto.TeamResponse;
import com.example.model.Team;
import com.example.model.constant.TeamSearchType;
import com.example.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    // Team 등록
    @PostMapping
    public ResponseEntity<TeamResponse> create(@RequestBody TeamRequest request){
        log.info("CREATE");

        if (ObjectUtils.isEmpty(request.getName()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getLocation()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getMascot()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getFoundedDate()))
            request.setFoundedDate(LocalDate.now().toString());

        Team result = this.teamService.addTeam(request);
        return ResponseEntity.ok(TeamResponse.from(result));
    }

    // Team Id 기반 개별 조회
    @GetMapping("{id}")
    public ResponseEntity<TeamResponse> readOne(@PathVariable Long id){
        log.info("READ One");
        Team result = this.teamService.searchById(id);
        log.info(TeamResponse.from(result).toString());
        return ResponseEntity.ok(TeamResponse.from(result));
        //return ResponseEntity.ok().build();
    }

    // Team 개별 수정
    @PatchMapping("{id}")
    public ResponseEntity<TeamResponse> update(@PathVariable Long id, @RequestBody TeamRequest request) {
        log.info("UPDATE");
        Team result = this.teamService.updateById(id, request);
        return ResponseEntity.ok(TeamResponse.from(result));
    }

    // Team 전체 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        log.info("DELETE ALL");
        this.teamService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Team 개별 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        log.info("DELETE");
        this.teamService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    // 페이징 기반 Team 조회
    //   - 검색 필터 구현 옵션
    //   - 해당 Team의 Member List으로 응답
    @GetMapping
    public ResponseEntity<Page<TeamResponse>> readTeamMembersByFilter(
            @RequestParam(required = false) TeamSearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10) Pageable pageable
    ){
        Page<TeamResponse> teamResponses = teamService.searchTeamByFilter(searchType, searchValue, pageable);
        return ResponseEntity.ok(teamResponses);
    }


}

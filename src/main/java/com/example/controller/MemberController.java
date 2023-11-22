package com.example.controller;

import com.example.dto.MemberRequest;
import com.example.dto.MemberResponse;
import com.example.dto.TeamRequest;
import com.example.dto.TeamResponse;
import com.example.model.Member;
import com.example.model.Team;
import com.example.model.constant.MemberSearchType;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    // Member 등록
    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody MemberRequest request){
        log.info("CREATE");

        if (ObjectUtils.isEmpty(request.getFirstName()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getLastName()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getAddress()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getTeamId()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getJoinedDate()))
            request.setJoinedDate(LocalDate.now().toString());

        Member result = this.memberService.addMember(request);
        return ResponseEntity.ok(MemberResponse.from(result));
    }

    // Member Id 기반 개별 조회
    @GetMapping("{id}")
    public ResponseEntity<MemberResponse> readOne(@PathVariable Long id){
        log.info("READ One");
        Member result = this. memberService.searchById(id);
        return ResponseEntity.ok(MemberResponse.from(result));
    }

    // Member 개별 수정
    @PatchMapping("{id}")
    public ResponseEntity<MemberResponse> update(@PathVariable Long id, @RequestBody MemberRequest request) {
        log.info("UPDATE");
        Member result = this.memberService.updateById(id, request);
        return ResponseEntity.ok(MemberResponse.from(result));
    }

    // Member 전체 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        log.info("DELETE ALL");
        this.memberService.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Member 개별 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        log.info("DELETE");
        this.memberService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*
    - 페이징 기반 Member 조회, 검색 필터 구현 옵션
    */
    @GetMapping
    public ResponseEntity<Page<MemberResponse>> readMemberByFilter(
            @RequestParam(required = false, name = "searchType") MemberSearchType searchType,
            @RequestParam(required = false, name = "searchValue") String searchValue,
            @PageableDefault(size = 10) Pageable pageable
            ){
        Page<MemberResponse> memberResponses = memberService.searchMemberByFilter(searchType, searchValue, pageable);
        log.info("memberResponses " + memberResponses.toString());
        return ResponseEntity.ok(memberResponses);
    }
}

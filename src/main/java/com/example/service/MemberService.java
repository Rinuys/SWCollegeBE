package com.example.service;

import com.example.dto.MemberRequest;
import com.example.dto.MemberResponse;
import com.example.model.Member;
import com.example.model.constant.MemberSearchType;
import com.example.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TeamService teamService;

    // ID 기반 개별 조회
    public Member searchById(Long id){
        return this.memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // 페이징 기반 Member 조회, 검색 필터 구현 옵션
    @Transactional(readOnly = true)
    public Page<MemberResponse> searchMemberByFilter(MemberSearchType searchType, String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return memberRepository.findAll(pageable).map(MemberResponse::from);
        }
        switch (searchType){
            case FIRSTNAME:
                return memberRepository.findByFirstName(searchKeyword, pageable).map(MemberResponse::from);
            case LASTNAME:
                return memberRepository.findByLastName(searchKeyword, pageable).map(MemberResponse::from);
            case ADDRESS:
                return memberRepository.findByAddress(searchKeyword, pageable).map(MemberResponse::from);
            case TEAM:
                return memberRepository.findByTeam_Id(Long.valueOf(searchKeyword), pageable).map(MemberResponse::from);
            default:
                return memberRepository.findAll(pageable).map(MemberResponse::from);
        }
    }

    // Member 등록
    public Member addMember(MemberRequest memberRequest){
        Member newMember = new Member();
        newMember.setFirstName(memberRequest.getFirstName());
        newMember.setLastName(memberRequest.getLastName());
        newMember.setAddress(memberRequest.getAddress());
        newMember.setJoinedDate(memberRequest.getJoinedDate());
        newMember.setTeam(teamService.searchById(memberRequest.getTeamId()));

        return memberRepository.save(newMember);

    }

    // Member 개별 수정
    public Member updateById(Long id, MemberRequest memberRequest){
        Member member = searchById(id);

        if(memberRequest.getFirstName() != null){
            member.setFirstName(memberRequest.getFirstName());
        }
        if(memberRequest.getLastName() != null){
            member.setLastName(memberRequest.getLastName());
        }
        if(memberRequest.getAddress() != null){
            member.setAddress(memberRequest.getAddress());
        }
        if(memberRequest.getJoinedDate() != null){
            member.setJoinedDate(memberRequest.getJoinedDate());
        }
        if(memberRequest.getTeamId() != null){
            member.setTeam(teamService.searchById(memberRequest.getTeamId()));
        }

        return this.memberRepository.save(member);
    }

    // Team 전체 삭제
    public void deleteAll(){
        this.memberRepository.deleteAll();
    }

    // Team 개별 삭제
    public void deleteById(Long id){
        this.memberRepository.deleteById(id);
    }



}

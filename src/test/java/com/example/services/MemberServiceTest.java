package com.example.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.example.dto.MemberRequest;
import com.example.model.Member;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void add(){
        when(this.memberRepository.save(any(Member.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        MemberRequest request = new MemberRequest();
        request.setFirstName("hoho");
        request.setLastName("haha");
        request.setAddress("asdf");
        request.setJoinedDate("1234:12:34");

        Member actual = this.memberService.addMember(request);

        assertEquals(1L, actual.getId());
        assertEquals("hoho", actual.getFirstName());

    }

}

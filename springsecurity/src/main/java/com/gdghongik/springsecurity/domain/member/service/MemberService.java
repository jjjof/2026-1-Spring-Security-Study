package com.gdghongik.springsecurity.domain.member.service;

import com.gdghongik.springsecurity.domain.member.dto.MemberCreateRequest;
import com.gdghongik.springsecurity.domain.member.dto.MemberInfoResponse;
import com.gdghongik.springsecurity.domain.member.dto.MemberUpdateRequest;
import com.gdghongik.springsecurity.domain.member.entity.Member;
import com.gdghongik.springsecurity.global.exception.CustomException;
import com.gdghongik.springsecurity.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.gdghongik.springsecurity.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Transactional
    public void createMember(MemberCreateRequest request) {
        // 중복되는 유저네임이 있으면 에러
        if (true) {
            throw new CustomException(MEMBER_USERNAME_DUPLICATE);
        }

        Member member = new Member(request.getUsername(), request.getPassword());

        // 멤버를 저장한다
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> getMembers() {
        // 모든 멤버를 가져온다
        List<MemberInfoResponse> list = new ArrayList<>();

    }

    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberByUsername(String username) {
        // 해당하는 멤버를 가져온다. 없으면 에러

    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest request) {
        // 해당하는 멤버를 가져온다. 없으면 에러

        // 해당하는 멤버 정보를 갱신한다

    }

    @Transactional
    public void deleteMember(Long memberId) {
        // 해당하는 멤버를 가져온다. 없으면 에러

        // 해당 멤버를 삭제한다

    }
}

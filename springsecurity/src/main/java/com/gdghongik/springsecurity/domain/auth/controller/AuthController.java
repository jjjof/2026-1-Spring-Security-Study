package com.gdghongik.springsecurity.domain.auth.controller;

import com.gdghongik.springsecurity.domain.auth.dto.LoginRequest;
import com.gdghongik.springsecurity.domain.member.dto.LoginResponse;
import com.gdghongik.springsecurity.domain.member.dto.MemberCreateRequest;
import com.gdghongik.springsecurity.domain.member.service.MemberService;
import com.gdghongik.springsecurity.global.security.CustomUserDetails;
import com.gdghongik.springsecurity.global.security.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody MemberCreateRequest request) {
        memberService.createMember(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {

        // AuthenticationManager가 다룰 수 있도록 요청을 토큰 객체에 담는다
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.username(), request.password());

        // AuthenticationManager가 객체를 통해 인증 시도, 실패하면 Exception
        Authentication authentication = authenticationManager.authenticate(token);

        // 인증된 사용자 정보로 JWT Access Token 발급
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();
        String accessToken = jwtProvider.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(accessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpRequest) {

        return ResponseEntity.ok().build();
    }
}
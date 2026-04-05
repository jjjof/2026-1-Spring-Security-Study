1. SecurityFilterChain
- 기본적으로 DefaultSecurityFilterChain 자동 등록
- 로그인 페이지 자동 생성 및 인증 요구
- 모든 보안 설정의 시작점

2. Session 기반 인증 개념
- 로그인 시 세션 ID 발급 (JSESSIONID)
- 브라우저 쿠키에 저장
- 이후 요청마다 세션 ID 전달
- 서버는 세션 저장소에서 사용자 정보 확인

3. Stateful 특징
- 서버가 사용자 상태를 기억
- 장점: 편리함
- 단점: 서버 자원 사용, 확장성 문제

4. HttpSecurity
- 보안 설정을 위한 빌더 객체
- prototype scope → 매 요청마다 새 객체 생성
- 설정 충돌 방지 목적

5. 로그인 플로우
1) 로그인 요청 → AuthenticationToken 변환
2) AuthenticationManager 인증
3) SecurityContext에 인증 정보 저장
4) 세션 생성 및 JSESSIONID 발급
5) 세션에 인증 정보 저장

6. AuthenticationManager
- 인증 수행 핵심 컴포넌트
- UserDetailsService + PasswordEncoder 사용

7. 전체 흐름
요청 → FilterChain → 인증 → SecurityContext → 세션 → 유지

- 시작: SecurityFilterChain
- 인증: AuthenticationManager
- 상태 유지: Session
- 특징: Stateful
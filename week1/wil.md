인증 - 사용자가 누구인지 확인
인가 - 그 사용자가 무엇을 할 수 있는지 결정

Spring Security의 역할
“애플리케이션의 보안을 담당하는 프레임워크”

비유
인증 = 신분증 검사
인가 = 출입증 확인
전체 시스템 = 건물 보안 시스템

인증 → 신분증 확인 후 출입증 발급
인가 → 출입증으로 접근 가능 여부 판단

"개발자는 직접 구현하지 않고 Spring Security가 이 과정을 대신 처리"

---

핵심 구조: Servlet Filter 기반 동작

"Spring Security의 본질은 필터"

Servlet Filter란:
요청(Request)과 응답(Response)을 가로채서 처리
ex. 로깅, 인코딩, 인증 처리

Spring Security 방식:
보안 기능을 필터 체인으로 삽입
이름: SecurityFilterChain

흐름 구조:
HTTP 요청
 → Servlet Filters
 → SecurityFilterChain (여기서 인증/인가 처리)
 → Controller

"컨트롤러에 도달하기 전에 보안 검사를 모두 끝냄"

---

인증 흐름 (로그인 과정)

로그인 요청이 들어왔을 때 내부 동작:
1. 사용자가 ID/PW 제출
2. 인증 필터가 요청을 가로챔
3. AuthenticationManager로 위임
4. UserDetailsService가 DB 조회
5. 결과 반환

성공 → 인증 완료
실패 → 인증 실패

핵심 구조:
Filter → Manager → Service → DB

각 영역 역할:
Filter: 요청 수집
Manager: 인증 로직 총괄
Service: 사용자 정보 조회

---

인가 흐름 (접근 제어)

"인증 이후, 특정 URL 접근 시 권한 검사 수행"

예시 정책
/admin    → ADMIN만 접근 가능
/mypage   → 로그인 사용자만 접근 가능
/         → 누구나 접근 가능
코드 개념
hasRole("ADMIN") → 특정 권한 필요
authenticated() → 로그인 필요
permitAll() → 제한 없음

---

JpaRepository :
crud가 정의되어 있음, 상속으로 사용 가능
메서드를 네이밍해줘도 jpa 쿼리 자동생성해줌

optional = null포장
1. Session 기반 인증
서버가 사용자 로그인 상태를 세션(Session)으로 저장
클라이언트는 세션 ID만 전달
서버가 매 요청마다 세션 조회
특징 :
stateful
서버 메모리/DB에 의존
문제점 :
서버 확장성 떨어짐
서버가 상태를 계속 들고 있어야 함

2. Token 기반 인증
구조 :
로그인 성공
서버가 토큰 발급
클라이언트가 토큰 저장
요청 시 토큰 전송
서버는 토큰만 보고 인증
특징 : 
stateless
서버가 사용자 상태 저장 X

3. JWT (JSON Web Token)
등장 배경 :
형식이 표준화되어야 함
변조가 어려워야 함

구조 :
Header
    어떤 알고리즘으로 서명했는지
Payload
    사용자 정보 (claims)
Signature
    Header + Payload를 비밀키로 서명한 값

인증 과정 :
토큰 추출
토큰 파싱
서명 검증 (변조 체크)
사용자 정보 추출

주의점 : 
유효기간 있으면 -> 공격 제한 가능, 없으면 -> 공격자가 계속 사용 가능
JWT는 암호화가 아닌 서명임

4. 로그인 흐름
로그인 요청 → authToken 변환
AuthenticationManager로 인증
실패 → Exception 발생
인증 성공 → JWT 발급
클라이언트에 전달

5. Authorization Header 로 JWT를 보낼 때 관례적으로 Bearer 붙이는 이유
Bearer :
HTTP는 인증을 "Authorization: <type> <credentials>" 형식으로 처리
Bearer은 이 중 type 태그로, 소유 자체로 권한이 되므로 추가 인증이 필요없음

JWT를 Bearer로 보내는 이유 :
HTTP 표준을 따르기 위해
OAuth 2.0 및 REST API에서 일반적으로 사용하는 방식
서버가 토큰을 쉽게 구분하고 처리할 수 있도록 하기 위해
JWT처럼 자체적으로 인증 정보를 담고 있는 토큰을 간편하게 전달하기 위해

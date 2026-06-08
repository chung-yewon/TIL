# 🦁 Likelion Member Manager

## 📌 프로젝트 소개
멋쟁이사자처럼 멤버와 과제를 관리하는 REST API 서버입니다.
전역 예외 처리(@RestControllerAdvice)를 적용하여 일관된 에러 응답을 제공하고,
프론트엔드와 JSON으로 통신하는 전체 흐름을 구현했습니다.

## 🛠 기술 스택
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL 8.0
- Gradle

## ▶ 실행 방법
1. MySQL에서 데이터베이스 생성
```sql
CREATE DATABASE likelion_pbl;
```
2. `application.properties`에서 DB 비밀번호 설정
3. 애플리케이션 실행
```bash
./gradlew bootRun
```
4. 브라우저에서 접속: http://localhost:8080

## 📋 API 목록

### Member API
| HTTP 메서드 | URI | 설명 |
|---|---|---|
| POST | /members/lions | LION 멤버 등록 |
| POST | /members/staffs | STAFF 멤버 등록 |
| GET | /members | 전체 멤버 조회 |
| GET | /members?part= | 파트별 멤버 필터링 |
| GET | /members/{id} | 멤버 단건 조회 |
| PUT | /members/lions/{id} | LION 멤버 수정 |
| PUT | /members/staffs/{id} | STAFF 멤버 수정 |
| DELETE | /members/{id} | 멤버 삭제 |

### Assignment API
| HTTP 메서드 | URI | 설명 |
|---|---|---|
| POST | /members/{memberId}/assignments | 과제 등록 |
| GET | /assignments | 전체 과제 조회 |
| GET | /assignments/search?keyword= | 과제 제목 검색 |
| GET | /members/{memberId}/assignments | 멤버별 과제 조회 |
| GET | /assignments/{id} | 과제 단건 조회 |
| PUT | /assignments/{id} | 과제 수정 |
| DELETE | /assignments/{id} | 과제 삭제 |

## 📁 프로젝트 구조
```
src/main/java/com/likelion/pbl_w8/
├── controller/        # MemberController
├── service/           # MemberService
├── repository/        # MemberRepository
├── domain/            # Member, RoleType
├── dto/               # 요청/응답 DTO
├── assignment/        # 과제 관련 Controller, Service, Repository, DTO
└── global/
    ├── exception/     # 커스텀 예외 클래스, GlobalExceptionHandler
    └── dto/           # ErrorResponse
```
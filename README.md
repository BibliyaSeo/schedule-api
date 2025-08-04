# 🗓️ Schedule API

일정 관리 API 서버입니다. <br>
Java와 Spring Boot를 활용하여 일정 생성, 조회, 수정, 삭제 기능을 구현했습니다.<br>
댓글 작성 및 댓글 포함 조회 기능도 포함되어 있습니다.

---

## 🧩 ERD (Entity Relationship Diagram)

<img width="376" height="397" alt="Screenshot 2025-08-04 at 9 34 00 AM" src="https://github.com/user-attachments/assets/31f5a637-e640-491c-b79d-fd64fed996e2" />

---

## 📌 프로젝트 개요
프로젝트 목적:
RESTful API 개발 및 서버-DB 연동 이해를 바탕으로 실무 수준의 백엔드 시스템 구축 능력 향상.

주요 학습 목표:<br>
- 3 Layer Architecture 설계<br>
- JPA를 활용한 데이터베이스 연동<br>
- HTTP 요청/응답 처리 (ResponseEntity)<br>
- API 명세에 맞춘 CRUD 구현<br>
- Postman을 활용한 API 테스트<br>

---

## 🛠 기술 스택
Language: Java 17<br>
Framework: Spring Boot<br>
ORM: Spring Data JPA<br>
IDE: IntelliJ IDEA<br>
Build Tool: Gradle<br>
Database: MySQL

---

## ✅ 주요 기능
필수 기능
- 일정 생성 (비밀번호 저장, JPA Auditing 적용)<br>
- 전체 일정 조회 (작성자명 필터링 가능, 수정일 기준 정렬)<br>
- 일정 단건 조회 (ID 기준)<br>
- 일정 수정 (비밀번호 일치 시만 가능)<br>
- 일정 삭제 (비밀번호 일치 시만 가능)<br>

도전 기능
- 일정에 댓글 작성 (최대 10개)<br>
- 일정 단건 조회 시 댓글 함께 응답<br>

<b>모든 API 응답에서 비밀번호는 제외됩니다.</b>

---

## 📁 패키지 구조(요약)

<pre> <code>
# org.example.scheduleapi
├── controller
│   ├── CommentController
│   └── ScheduleController
│
├── dto
│   ├── CommentRequestDto
│   ├── CommentResponseDto
│   └── ...
│
├── entity
│   ├── Comment
│   └── Schedule
│
├── repository
│   ├── CommentRepository
│   └── ScheduleRepository
│
├── service
│   ├── CommentService
│   ├── CommentServiceImpl
│   └── ...
│
└── ScheduleApiApplication
</code> </pre>

---
## 🧪 API 실행 및 테스트
- Postman을 활용해 API 테스트를 진행합니다.<br>
- 각 요청에 대한 응답은 ResponseEntity를 통해 처리됩니다.<br>
- API는 명세에 따라 설계되었으며, 요구사항에 맞춰 동작합니다.<br>

---

## 🛠️ API 명세서

### POST /schedules - 일정 생성

- **설명**: 새로운 일정을 생성합니다.
- **요청 본문** (`application/json`):

```json
{
  "title": "제목",
  "contents": "내용",
  "writer": "작성자",
  "password": "비번"
}
```

### GET /schedules?writer=작성자 - 일정 조회 (전체 또는 작성자별)

- **설명**:  
  전체 일정을 조회하거나, 특정 작성자의 일정만 필터링해서 조회합니다.

- **쿼리 파라미터 (선택)**:
    - `writer` (optional): 작성자 이름
        - 이 값을 입력하면 해당 작성자의 일정만 조회
        - 생략하면 전체 일정 조회

### GET /schedules/{id} - 일정 단건 조회

- **설명**:  
  ID를 기준으로 하나의 일정을 조회합니다.

### PATCH /schedules/{id} - 일정 수정

- **설명**:  
  비밀번호를 검증한 후 일정을 수정합니다. (제목과 작성자만 수정 가능)

- **요청 본문** (`application/json`):

```json
{
  "title": "수정된 제목",
  "writer": "작성자",
  "password": "비밀번호"
}
```

### DELETE /schedules/{id} - 일정 삭제

- **설명**:  
  비밀번호를 입력해 해당 일정을 삭제합니다.
- **요청 본문** (`application/json`):

```json
{
  "password": "비밀번호"
}
```

### POST /schedules/{id}/comments - 댓글 생성

- **설명**: 새로운 댓글을 생성합니다.
- **요청 본문** (`application/json`):

```json
{
  "contents": "댓글 내용11",
  "writer": "댓글 작성자11",
  "password": "댓글비번6"
}
```

### GET /schedules/{id}/comments - 일정 아이디에 따른 댓글 조회

- **설명**:  
  ID를 기준으로 하나의 일정에 있는 댓글을 조회합니다

### PATCH /comments/{id} - 댓글 수정

- **설명**:  
  비밀번호를 검증한 후 댓글을 수정합니다. (내용과 작성자 수정 가능)

- **요청 본문** (`application/json`):

```json
{
  "contents": "수정된 내용",
  "writer": "수정된 작성자",
  "password": "댓글비번2"
}
```

### DELETE /comments/{id} - 댓글 삭제

- **설명**:  
  비밀번호를 입력해 해당 댓글을 삭제합니다.
- **요청 본문** (`application/json`):

```json
{
  "password": "비밀번호"
}
```

---

## 🔄 트러블 슈팅
https://www.notion.so/API-Lv1-Lv6-24255d5f00a080b3b2ecf5fdab82c12a?source=copy_link

## Swagger
https://app.swaggerhub.com/apis-docs/bibliyaseoorganizati/schedule-api/1.0.0#/



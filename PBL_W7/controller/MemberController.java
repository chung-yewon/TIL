package com.example.pbl_w7.controller;

import com.example.pbl_w7.domain.role.Lion;
import com.example.pbl_w7.domain.role.Member;
import com.example.pbl_w7.domain.role.Staff;
import com.example.pbl_w7.dto.*;
import com.example.pbl_w7.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // [지침 4번] REST API 컨트롤러로 등록합니다.
@RequestMapping("/members") // [지침 4번] 기본 URL 경로를 /members로 통합합니다.
public class MemberController {

    private final MemberService memberService;

    // [지침 4번] 생성자 주입 방식으로 서비스 계층을 주입받습니다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // [지침 5번] 1. Lion 등록 API (성공: 201 Created / 중복: 409 Conflict)
    @PostMapping("/lions")
    public ResponseEntity<?> createLion(@RequestBody LionCreateRequest request) {
        Lion lion = memberService.createLion(request);
        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 반환
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from(lion)); // 201 + DTO 반환
    }

    // [지침 5번] 2. Staff 등록 API (성공: 201 Created / 중복: 409 Conflict)
    @PostMapping("/staffs")
    public ResponseEntity<?> createStaff(@RequestBody StaffCreateRequest request) {
        Staff staff = memberService.createStaff(request);
        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 반환
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from(staff)); // 201 + DTO 반환
    }

    // [지침 6번] 3. 단일 멤버 조회 API (성공: 200 OK + 역할별 DTO / 실패: 404 Not Found)
    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable String name) {
        Member member = memberService.findMember(name);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 반환
        }

        // 실제 자식 객체의 타입에 맞춰서 알맞은 응답 DTO 바구니에 매핑해 줍니다.
        if (member instanceof Lion) {
            return ResponseEntity.ok(LionResponse.from((Lion) member));
        } else if (member instanceof Staff) {
            return ResponseEntity.ok(StaffResponse.from((Staff) member));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // [지침 7번] 4. Lion 정보 수정 API (성공: 200 OK + DTO / 실패: 404 Not Found)
    @PutMapping("/lions/{name}")
    public ResponseEntity<?> updateLion(@PathVariable String name, @RequestBody LionUpdateRequest request) {
        Lion lion = memberService.updateLion(name, request);
        if (lion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 반환
        }
        return ResponseEntity.ok(LionResponse.from(lion));
    }

    // [지침 7번] 5. Staff 정보 수정 API (성공: 200 OK + DTO / 실패: 404 Not Found)
    @PutMapping("/staffs/{name}")
    public ResponseEntity<?> updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest request) {
        Staff staff = memberService.updateStaff(name, request);
        if (staff == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 반환
        }
        return ResponseEntity.ok(StaffResponse.from(staff));
    }

    // [지침 8번] 6. 멤버 삭제 API (성공: 204 No Content / 실패: 404 Not Found)
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable String name) {
        boolean isDeleted = memberService.deleteMember(name);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 반환
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 반환
    }
}
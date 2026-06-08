package com.likelion.pbl_w8.assignment.controller;

import com.likelion.pbl_w8.assignment.dto.AssignmentCreateRequest;
import com.likelion.pbl_w8.assignment.dto.AssignmentResponse;
import com.likelion.pbl_w8.assignment.dto.AssignmentUpdateRequest;
import com.likelion.pbl_w8.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // 과제 등록
    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(assignmentService.createAssignment(memberId, request));
    }

    // 멤버별 과제 목록 조회
    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByMember(
            @PathVariable Long memberId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByMember(memberId));
    }

    // 전체 과제 조회 (신규)
    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    // 과제 제목 검색 (신규)
    @GetMapping("/assignments/search")
    public ResponseEntity<List<AssignmentResponse>> searchAssignments(
            @RequestParam String keyword) {
        return ResponseEntity.ok(assignmentService.searchAssignments(keyword));
    }

    // 과제 단건 조회
    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignment(id));
    }

    // 과제 수정
    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, request));
    }

    // 과제 삭제
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
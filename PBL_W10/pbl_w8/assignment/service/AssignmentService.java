package com.likelion.pbl_w8.assignment.service;

import com.likelion.pbl_w8.assignment.domain.Assignment;
import com.likelion.pbl_w8.assignment.dto.AssignmentCreateRequest;
import com.likelion.pbl_w8.assignment.dto.AssignmentResponse;
import com.likelion.pbl_w8.assignment.dto.AssignmentUpdateRequest;
import com.likelion.pbl_w8.assignment.repository.AssignmentRepository;
import com.likelion.pbl_w8.domain.Member;
import com.likelion.pbl_w8.global.exception.AssignmentNotFoundException;
import com.likelion.pbl_w8.global.exception.MemberNotFoundException;
import com.likelion.pbl_w8.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    // 과제 등록
    @Transactional
    public AssignmentResponse createAssignment(Long memberId, AssignmentCreateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + memberId));
        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        assignmentRepository.save(assignment);
        return AssignmentResponse.from(assignment);
    }

    // 멤버별 과제 목록 조회
    public List<AssignmentResponse> getAssignmentsByMember(Long memberId) {
        return assignmentRepository.findByMemberId(memberId)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    // 전체 과제 조회 (신규)
    public List<AssignmentResponse> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    // 과제 제목 검색 (신규)
    public List<AssignmentResponse> searchAssignments(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    // 과제 단건 조회
    public AssignmentResponse getAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. id: " + id));
        return AssignmentResponse.from(assignment);
    }

    // 과제 수정
    @Transactional
    public AssignmentResponse updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. id: " + id));
        assignment.updateInfo(request.getTitle(), request.getDescription());
        return AssignmentResponse.from(assignment);
    }

    // 과제 삭제
    @Transactional
    public void deleteAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. id: " + id));
        assignmentRepository.delete(assignment);
    }
}
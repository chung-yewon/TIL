package com.likelion.pbl_w8.assignment.service;

import com.likelion.pbl_w8.assignment.domain.Assignment;
import com.likelion.pbl_w8.assignment.dto.AssignmentCreateRequest;
import com.likelion.pbl_w8.assignment.dto.AssignmentResponse;
import com.likelion.pbl_w8.assignment.dto.AssignmentUpdateRequest;
import com.likelion.pbl_w8.assignment.repository.AssignmentRepository;
import com.likelion.pbl_w8.domain.Member;
import com.likelion.pbl_w8.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) return null;

        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        assignmentRepository.save(assignment);
        return AssignmentResponse.from(assignment);
    }

    // 멤버별 과제 목록 조회
    public List<AssignmentResponse> getAssignmentsByMember(Long memberId) {
        return assignmentRepository.findByMemberId(memberId)
                .stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    // 과제 단건 조회
    public AssignmentResponse getAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment == null) return null;
        return AssignmentResponse.from(assignment);
    }

    // 과제 수정
    @Transactional
    public AssignmentResponse updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment == null) return null;

        assignment.updateInfo(request.getTitle(), request.getDescription());
        assignmentRepository.save(assignment);
        return AssignmentResponse.from(assignment);
    }

    // 과제 삭제
    @Transactional
    public boolean deleteAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment == null) return false;

        assignmentRepository.deleteById(id);
        return true;
    }
}
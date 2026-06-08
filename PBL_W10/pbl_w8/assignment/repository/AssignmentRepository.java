package com.likelion.pbl_w8.assignment.repository;

import com.likelion.pbl_w8.assignment.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByMemberId(Long memberId);
    List<Assignment> findByTitleContaining(String keyword);
}
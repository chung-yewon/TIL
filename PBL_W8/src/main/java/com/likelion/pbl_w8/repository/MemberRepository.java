package com.likelion.pbl_w8.repository;

import com.likelion.pbl_w8.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
}
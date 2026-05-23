package com.example.pbl_w7.repository;

import com.example.pbl_w7.domain.role.Member;
import java.util.List;

public interface MemberRepository {
    void save(Member member);
    Member findByName(String name);
    List<Member> findAll();
    boolean isDuplicate(String name);
}
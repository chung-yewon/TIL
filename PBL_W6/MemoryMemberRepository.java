package com.example.demo.repository;
import com.example.demo.domain.Member;
import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class MemoryMemberRepository implements MemberRepository {
    private final List<Member> members = new ArrayList<>();

    @Override
    public void save(Member member) { members.add(member); }

    @Override
    public Member findByName(String name) {
        return members.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Member> findAll() { return new ArrayList<>(members); }

    @Override
    public boolean isDuplicate(String name) {
        return members.stream().anyMatch(m -> m.getName().equals(name));
    }
}
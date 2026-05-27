package com.likelion.pbl_w8.service;

import com.likelion.pbl_w8.domain.Member;
import com.likelion.pbl_w8.domain.RoleType;
import com.likelion.pbl_w8.dto.*;
import com.likelion.pbl_w8.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createLion(LionCreateRequest request) {
        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getGeneration(),
                RoleType.LION,
                request.getStudentId(),
                null
        );
        return memberRepository.save(member);
    }

    public Member createStaff(StaffCreateRequest request) {
        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getGeneration(),
                RoleType.STAFF,
                null,
                request.getPosition()
        );
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) return null;
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return memberRepository.save(member);
    }

    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) return null;
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return memberRepository.save(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
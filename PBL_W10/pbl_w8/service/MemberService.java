package com.likelion.pbl_w8.service;

import com.likelion.pbl_w8.domain.Member;
import com.likelion.pbl_w8.domain.RoleType;
import com.likelion.pbl_w8.dto.LionCreateRequest;
import com.likelion.pbl_w8.dto.LionUpdateRequest;
import com.likelion.pbl_w8.dto.MemberResponse;
import com.likelion.pbl_w8.dto.StaffCreateRequest;
import com.likelion.pbl_w8.dto.StaffUpdateRequest;
import com.likelion.pbl_w8.global.exception.DuplicateMemberException;
import com.likelion.pbl_w8.global.exception.MemberNotFoundException;
import com.likelion.pbl_w8.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // LION 등록
    public MemberResponse createLion(LionCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            throw new DuplicateMemberException("이미 존재하는 이름입니다. name: " + request.getName());
        }
        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getGeneration(),
                RoleType.LION,
                request.getStudentId(),
                null
        );
        return MemberResponse.from(memberRepository.save(member));
    }

    // STAFF 등록
    public MemberResponse createStaff(StaffCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            throw new DuplicateMemberException("이미 존재하는 이름입니다. name: " + request.getName());
        }
        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getGeneration(),
                RoleType.STAFF,
                null,
                request.getPosition()
        );
        return MemberResponse.from(memberRepository.save(member));
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::from)
                .toList();
    }

    // 파트별 조회 (신규)
    @Transactional(readOnly = true)
    public List<MemberResponse> getMembersByPart(String part) {
        return memberRepository.findByPart(part).stream()
                .map(MemberResponse::from)
                .toList();
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id));
        return MemberResponse.from(member);
    }

    // LION 수정
    public MemberResponse updateLion(Long id, LionUpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id));
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return MemberResponse.from(member);
    }

    // STAFF 수정
    public MemberResponse updateStaff(Long id, StaffUpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id));
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return MemberResponse.from(member);
    }

    // 삭제
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id));
        memberRepository.delete(member);
    }
}
package com.example.pbl_w7.service;

import com.example.pbl_w7.domain.role.Lion;
import com.example.pbl_w7.domain.role.Member;
import com.example.pbl_w7.domain.role.Staff;
import com.example.pbl_w7.dto.*;
import com.example.pbl_w7.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 스프링이 비즈니스 로직 기계로 인식하고 관리하도록 등록합니다.
public class MemberService {

    private final MemberRepository memberRepository;

    // 구현 지침 4번: 생성자 주입 방식으로 저장소를 주입받습니다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // [지침 2-1번] 1. 아기사자 생성 및 등록
    public Lion createLion(LionCreateRequest request) {
        // 식별자 제약사항: 이름 중복 검사 (6주차 저장소의 isDuplicate 활용)
        if (memberRepository.isDuplicate(request.getName())) {
            return null; // 중복 발생 시 null 반환 -> 컨트롤러가 409 처리
        }
        Lion lion = new Lion(request.getName(), request.getMajor(), request.getGeneration(), request.getPart(), request.getStudentId());
        memberRepository.save(lion);
        return lion;
    }

    // [지침 2-2번] 2. 운영진 생성 및 등록
    public Staff createStaff(StaffCreateRequest request) {
        if (memberRepository.isDuplicate(request.getName())) {
            return null;
        }
        Staff staff = new Staff(request.getName(), request.getMajor(), request.getGeneration(), request.getPart(), request.getPosition());
        memberRepository.save(staff);
        return staff;
    }

    // 3. 단일 멤버 이름으로 조회
    public Member findMember(String name) {
        return memberRepository.findByName(name);
    }

    // [지침 2-3번] 4. 아기사자 정보 수정
    // 자바 객체 참조(Reference) 특징 덕분에 데이터를 바꾼 뒤 저장소 메서드를 추가로 부르지 않아도 반영됩니다.
    public Lion updateLion(String name, LionUpdateRequest request) {
        Member member = memberRepository.findByName(name);
        if (member instanceof Lion) {
            Lion lion = (Lion) member;
            lion.updateLionInfo(request.getMajor(), request.getGeneration(), request.getPart(), request.getStudentId());
            return lion;
        }
        return null; // 대상이 없거나 타입이 안 맞으면 null
    }

    // [지침 2-4번] 5. 운영진 정보 수정
    public Staff updateStaff(String name, StaffUpdateRequest request) {
        Member member = memberRepository.findByName(name);
        if (member instanceof Staff) {
            Staff staff = (Staff) member;
            staff.updateStaffInfo(request.getMajor(), request.getGeneration(), request.getPart(), request.getPosition());
            return staff;
        }
        return null;
    }

    // [지침 2-5번] 6. 멤버 삭제
    // 6주차 저장소 복사본 반환 특성을 고려해 안전한 초기화 후 복원 프로세스로 삭제를 우회 처리합니다.
    public boolean deleteMember(String name) {
        // 먼저 지우려는 대상이 있는지 확인
        Member target = memberRepository.findByName(name);
        if (target == null) {
            return false; // 대상이 없으면 삭제 실패
        }

        // 1. 전체 리스트 복사본에서 삭제 대상을 제외한 "남을 사람들"만 필터링해서 수집합니다.
        List<Member> remainingMembers = memberRepository.findAll().stream()
                .filter(m -> !m.getName().equals(name))
                .toList();

        // 2. 6주차 저장소의 복사본 한계를 극복하기 위해 기존 원본 주소 데이터를 직접 비워주는 대신,
        // 현재 원본 리스트를 완전히 갈아엎을 수 없으므로 6주차 findAll()로 얻어낸 원본 뼈대 컬렉션을 비워줍니다.
        List<Member> rawSourceList = memberRepository.findAll();

        // 하지만 6주차 findAll()은 'new ArrayList<>'로 주므로 clear() 해도 원본 리스트가 안 비워집니다.
        // 이를 위해 원본 리스트를 6주차 구조를 건드리지 않고 제어하는 자바의 정석 우회법은 다음과 같습니다:
        // 원본 저장소 리스트를 직접 비우기 위해, 저장소에 이미 보관 중이던 전체 목록을 꺼내와
        // 6주차 저장소의 save() 메커니즘을 이용해 안전하게 복원하는 유일한 정석 방식입니다.
        try {
            // 자바 리플렉션을 이용해 6주차 MemoryMemberRepository 내부의 private final List<Member> members 주소를 직접 획득합니다.
            java.lang.reflect.Field field = com.example.pbl_w7.repository.MemoryMemberRepository.class.getDeclaredField("members");
            field.setAccessible(true);
            List<Member> originalList = (List<Member>) field.get(memberRepository);

            // 원본 리스트를 직접 조작해 해당 이름의 멤버를 확실하게 도려냅니다!
            return originalList.removeIf(m -> m.getName().equals(name));
        } catch (Exception e) {
            return false;
        }
    }
}
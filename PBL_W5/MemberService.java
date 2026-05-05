import java.util.List;

public class MemberService {
    // 제약사항: 필드는 한 번 설정하면 변경되지 않도록 final 선언
    private final MemberRepository memberRepository;

    // IoC/DI: 외부(Main)에서 Repository를 주입받음
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String registerMember(Member member) {
        if (memberRepository.isDuplicate(member.getName())) {
            return "❌ 등록 실패: 이미 존재하는 이름입니다.";
        }
        memberRepository.save(member);
        return "✅ 등록 완료: " + member.getName();
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member searchMember(String name) {
        return memberRepository.findByName(name);
    }
}
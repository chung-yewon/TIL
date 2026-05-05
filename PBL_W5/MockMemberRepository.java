import java.util.List;

public class MockMemberRepository implements MemberRepository {
    @Override
    public void save(Member member) { /* Mock은 저장하지 않음 */ }

    @Override
    public Member findByName(String name) {
        return new Member("아기사자", "김더미", "컴퓨터공학과", 14, "백엔드", "20240001");
    }

    @Override
    public List<Member> findAll() {
        return List.of(new Member("운영진", "이관리", "소프트웨어학과", 13, "기획", "20230001"));
    }

    @Override
    public boolean isDuplicate(String name) { return false; }
}
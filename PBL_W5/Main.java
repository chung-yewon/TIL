import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MemberRepository repository;

        // 1. 저장소 선택 (의존성 조립)
        System.out.println("🔧 저장소를 선택하세요:");
        System.out.println("1. MemoryMemberRepository (실제 저장)");
        System.out.println("2. MockMemberRepository (더미 데이터)");
        System.out.print("선택: ");
        int repoChoice = sc.nextInt();
        sc.nextLine();

        if (repoChoice == 1) repository = new MemoryMemberRepository();
        else repository = new MockMemberRepository();

        // 2. Service에 주입
        MemberService service = new MemberService(repository);

        // 3. 메인 메뉴 실행
        while (true) {
            System.out.println("\n🦁 ===== 멋사 멤버 관리 시스템 (Step 2: DI 적용) ===== 🦁");
            System.out.println("1. ➕ 멤버 등록");
            System.out.println("2. 📋 전체 멤버 조회");
            System.out.println("3. 🔍 이름으로 검색");
            System.out.println("4. 🚪 종료");
            System.out.print("선택: ");
            int menu = sc.nextInt();
            sc.nextLine();

            if (menu == 4) break;

            switch (menu) {
                case 1 -> { // 등록 화면
                    System.out.print("👤 역할 선택 (1: 아기사자, 2: 운영진): ");
                    String role = sc.nextInt() == 1 ? "아기사자" : "운영진"; sc.nextLine();
                    System.out.println("\n📝 정보 입력");
                    System.out.print("이름: "); String name = sc.nextLine();
                    System.out.print("전공: "); String major = sc.nextLine();
                    System.out.print("기수: "); int gen = sc.nextInt(); sc.nextLine();
                    System.out.print("파트: "); String part = sc.nextLine();
                    System.out.print("학번: "); String id = sc.nextLine();

                    System.out.println("\n" + service.registerMember(new Member(role, name, major, gen, part, id)));
                }
                case 2 -> { // 전체 조회
                    System.out.println("\n📋 ===== 전체 멤버 목록 =====");
                    service.getAllMembers().forEach(m -> {
                        System.out.println(m);
                        System.out.println("----------------------------");
                    });
                }
                case 3 -> { // 이름 검색
                    System.out.print("🔍 검색할 이름: ");
                    String searchName = sc.nextLine();
                    Member found = service.searchMember(searchName);
                    if (found != null) {
                        System.out.println("\n🎯 ===== 검색 결과 =====");
                        System.out.println(found);
                    } else {
                        System.out.println("❌ 해당 이름의 멤버가 없습니다.");
                    }
                }
            }
        }
    }
}
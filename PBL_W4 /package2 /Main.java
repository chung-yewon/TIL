package class4.package2;

import class4.role.Lion;
import class4.role.Role;
import class4.role.Staff;
import java.util.*;

public class Main {
    // [제약 사항] List와 Map 컬렉션을 사용하여 타입 안전성 보장
    private static List<Role> allMembers = new ArrayList<>();
    private static Map<String, List<Role>> partMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // [구현 지침] 종료를 선택하기 전까지 반복적으로 메뉴 출력
        while (true) {
            System.out.println("\n========== 🦁 멤버 관리 시스템 ==========");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 파트별 조회");
            System.out.println("5. 종료");
            System.out.print("선택: ");

            int choice = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            if (choice == 1) {
                // [등록 기능] 예시 화면 형식 반영
                System.out.println("\n— 📝 멤버 등록 —");
                System.out.print("역할 선택 (1: 아기사자, 2: 운영진): ");
                int type = sc.nextInt(); sc.nextLine();

                System.out.print("👤 이름: ");
                String name = sc.nextLine();

                // [구현 지침] 등록 전 동일한 이름 확인
                if (isDuplicate(name)) {
                    System.out.println("❌ 등록 실패: 이미 존재하는 이름입니다.");
                    continue;
                }

                System.out.print("🎓 전공: ");
                String major = sc.nextLine();
                System.out.print("📌 기수: ");
                int gen = sc.nextInt(); sc.nextLine();
                System.out.print("💻 파트 (백엔드/프론트엔드/기획/디자인): ");
                String part = sc.nextLine();

                Role newMember;
                if (type == 1) {
                    System.out.print("🆔 학번: ");
                    newMember = new Lion(name, major, gen, part, sc.nextLine());
                } else {
                    System.out.print("📋 담당 업무: ");
                    newMember = new Staff(name, major, gen, part, sc.nextLine());
                }

                // [Step 2] List와 Map에 동시 추가
                allMembers.add(newMember);
                partMap.computeIfAbsent(part, k -> new ArrayList<>()).add(newMember);

                System.out.println("✅ 등록 완료: " + name);

            } else if (choice == 2) {
                // [전체 조회] 예시 형식: 1. [아기사자] 김사자 - 14기
                System.out.println("\n— 📋 전체 멤버 목록 —");
                if (allMembers.isEmpty()) {
                    System.out.println("등록된 멤버가 없습니다.");
                } else {
                    for (int i = 0; i < allMembers.size(); i++) {
                        Role m = allMembers.get(i);
                        String roleStr = (m instanceof Lion) ? "아기사자" : "운영진";
                        System.out.println((i + 1) + ". [" + roleStr + "] " + m.getName() + " - " + m.getGeneration() + "기");
                    }
                }
                System.out.println("📊 총 " + allMembers.size() + "명");

            } else if (choice == 3) {
                // [이름 검색] 해당 멤버의 상세 정보 출력
                System.out.print("\n— 🔍 이름으로 검색 —\n검색할 이름: ");
                String searchName = sc.nextLine();
                boolean found = false;
                for (Role m : allMembers) {
                    if (m.getName().equals(searchName)) {
                        System.out.println(m.toString()); // Role에서 설정한 형식 출력
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("❓ 해당 이름을 찾을 수 없습니다.");

            } else if (choice == 4) {
                // [Step 2] 파트별 조회 기능
                System.out.println("\n— 📁 파트별 조회 —");
                if (partMap.isEmpty()) {
                    System.out.println("등록된 파트 정보가 없습니다.");
                } else {
                    System.out.println("📁 등록된 파트: " + partMap.keySet());
                    System.out.print("조회할 파트: ");
                    String targetPart = sc.nextLine();

                    List<Role> membersInPart = partMap.get(targetPart);
                    if (membersInPart != null) {
                        System.out.println("\n✨ [" + targetPart + " 파트 멤버]");
                        for (int i = 0; i < membersInPart.size(); i++) {
                            Role m = membersInPart.get(i);
                            String roleStr = (m instanceof Lion) ? "아기사자" : "운영진";
                            System.out.println((i + 1) + ". " + m.getName() + " (" + roleStr + ") - " + m.getGeneration() + "기");
                        }
                    } else {
                        System.out.println("❌ 해당 파트에 소속된 멤버가 없습니다.");
                    }
                }

            } else if (choice == 5) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
    }

    private static boolean isDuplicate(String name) {
        for (Role m : allMembers) {
            if (m.getName().equals(name)) return true;
        }
        return false;
    }
}

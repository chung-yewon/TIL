package class3;

import class3.role.Lion;
import class3.role.Role;
import class3.role.Staff;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 아기사자 정보 입력 (안내 이미지의 포맷 준수)
        System.out.println("======== 🦁 아기사자 정보 입력 ========");
        System.out.print("👤 이름: "); String lName = sc.nextLine();
        System.out.print("🎓 전공: "); String lMajor = sc.nextLine();
        System.out.print("📌 기수: "); int lGen = Integer.parseInt(sc.nextLine());
        System.out.print("📠 파트 (백엔드/프론트엔드/기획/디자인): "); String lPart = sc.nextLine();
        System.out.print("🆔 학번: "); String lId = sc.nextLine();

        // 다형성을 이용해 부모 타입(Role) 변수에 자식 객체 저장
        Role lion = new Lion(lName, lMajor, lGen, lPart, lId);

        System.out.println();

        // 2. 운영진 정보 입력
        System.out.println("======== 👤 운영진 정보 입력 ========");
        System.out.print("👤 이름: "); String sName = sc.nextLine();
        System.out.print("🎓 전공: "); String sMajor = sc.nextLine();
        System.out.print("📌 기수: "); int sGen = Integer.parseInt(sc.nextLine());
        System.out.print("📠 파트 (백엔드/프론트엔드/기획/디자인): "); String sPart = sc.nextLine();
        System.out.print("⭐ 직책 (대표/부대표/파트장/멘토): "); String sPos = sc.nextLine();

        Role staff = new Staff(sName, sMajor, sGen, sPart, sPos);

        // 3. 결과 출력 (제약사항 준수: if-else 없이 출력)
        System.out.println("\n======== 📋 결과 출력 ========");
        printRoleDetails(lion);
        System.out.println("------------------------------------");
        printRoleDetails(staff);
        System.out.println("------------------------------------");
    }

    // 이 메서드가 이번 과제의 핵심인 '다형성'을 보여주는 부분입니다!
    private static void printRoleDetails(Role role) {
        // 1단계: 공통 정보 출력
        System.out.println("🎭 역할: " + (role instanceof Lion ? "아기사자" : "운영진"));
        System.out.print("👤 이름: " + role.getName() + " | ");
        System.out.print("🎓 전공: " + role.getMajor() + " | ");
        System.out.print("📌 기수: " + role.getGeneration() + " | ");
        System.out.println("📠 파트: " + role.getPart());

        // 2단계: 특정 정보 출력 (각 자식 클래스가 오버라이딩한 내용이 나옵니다)
        System.out.println("🆔 " + role.getSpecificInfo());

        // 3단계: 과제 제출 가능 여부 (정책 객체에 판단을 위임)
        boolean canSubmit = role.getAssignmentPolicy().canSubmit();
        System.out.println("📝 과제 제출 가능 여부: " + (canSubmit ? "✅ 가능" : "❌ 불가"));
    }
}
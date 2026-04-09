package class2.package2; // 다른 패키지

import class2.package1.Lion;

public class step3 {
    public static void main(String[] args) {
        Lion lion = new Lion("정예원", "국제금융학과", 14);

        System.out.println("🦁 아기사자 객체를 생성합니다.");
        System.out.println("🦁 아기사자 정보를 출력합니다.");
        lion.printInfo();

        System.out.println("\n📌 Step 3-1. public 필드 접근을 시도합니다.");
        lion.name = "홍길동"; // 성공: public은 어디서든 접근 가능
        System.out.println("👉 name 필드 값을 변경합니다.");
        System.out.println("✅ public 필드 접근 성공");
        lion.printInfo();


        System.out.println("\n📌 Step 3-2. default 필드 접근 시도");
        lion.major = "경영학과";
        // 에러: major is not public in Lion; cannot be accessed from outside package

        System.out.println("\n📌 Step 3-3. private 필드 접근 시도");
        lion.generation = 15;
        // 에러: generation has private access in Lion
    }
}

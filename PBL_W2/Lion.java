package class2.package1;

public class Lion {
    // 제약사항: 서로 다른 접근 제어자 사용
    public String name;          // public
    String major;               // default
    private int generation;      // private

    // 제약사항: 생성자 내부에서 콘솔 출력 금지 (초기화만 담당)
    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    // Step 2를 위한 객체 내부 검증 메서드
    public boolean validateState() {
        if (name == null || name.isEmpty()) {
            System.out.println("❌ 이름이 비어 있습니다.");
            return false;
        }
        if (major == null || major.isEmpty()) {
            System.out.println("❌ 전공이 비어 있습니다.");
            return false;
        }
        if (generation < 1) {
            System.out.println("❌ 기수가 1 미만입니다.");
            return false;
        }
        return true;
    }

    // 정보 출력용 메서드
    public void printInfo() {
        System.out.println("👤 이름: " + name + " | 🎓 전공: " + major + " | 📌 기수: " + generation);
    }
}

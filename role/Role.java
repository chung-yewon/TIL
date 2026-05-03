package class4.role;

import class4.policy.AssignmentPolicy;

public abstract class Role {
    protected String name, major, part; // 자식 클래스에서 접근 가능하게 protected로 변경
    protected int generation;

    public Role(String name, String major, int generation, String part) {
        this.name = name; this.major = major; this.generation = generation; this.part = part;
    }

    public abstract AssignmentPolicy getAssignmentPolicy();
    public abstract String getSpecificInfo();
    public abstract String toFileFormat();

    public String getName() { return name; }
    public String getPart() { return part; }
    public int getGeneration() {
        return generation;
    }
    @Override
    public String toString() {
        // 결과 예시의 [검색 결과] 형식을 맞추기 위한 로직
        StringBuilder sb = new StringBuilder();
        sb.append("\n✨ [검색 결과]");
        sb.append("\n🎭 역할: ").append(this instanceof Lion ? "아기사자" : "운영진");
        sb.append("\n이름: ").append(name).append(" | 전공: ").append(major)
                .append(" | 기수: ").append(generation).append(" | 파트: ").append(part);
        sb.append("\n").append(getSpecificInfo()); // 학번 또는 담당 업무 출력

        // 과제 제출 가능 여부 (AssignmentPolicy 활용)
        boolean canSubmit = getAssignmentPolicy().canSubmit();
        sb.append("\n📝 과제 제출 가능 여부: ").append(canSubmit ? "✅ 가능" : "❌ 불가능");

        return sb.toString();
    }
}
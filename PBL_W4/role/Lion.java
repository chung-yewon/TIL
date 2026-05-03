package class4.role;

import class4.policy.AssignmentPolicy;
import class4.policy.LionAssignmentPolicy;

public class Lion extends Role {
    private String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    public AssignmentPolicy getAssignmentPolicy() {
        // class4.policy 패키지의 정책을 사용합니다.
        return new LionAssignmentPolicy();
    }

    @Override
    public String getSpecificInfo() {
        // 결과 예시 화면의 출력 형식을 맞춥니다.
        return "학번: " + studentId;
    }

    @Override
    public String toFileFormat() {
        // 파일 저장 시 구분자(,)를 사용합니다.
        return String.format("Lion,%s,%s,%d,%s,%s",
                getName(), major, generation, part, studentId);
    }
}
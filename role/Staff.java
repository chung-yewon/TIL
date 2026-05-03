package class4.role;

import class4.policy.AssignmentPolicy;
import class4.policy.StaffAssignmentPolicy;

public class Staff extends Role {
    private String staffRole;

    public Staff(String name, String major, int generation, String part, String staffRole) {
        super(name, major, generation, part);
        this.staffRole = staffRole;
    }

    @Override
    public AssignmentPolicy getAssignmentPolicy() {
        // class4.policy 패키지의 정책을 사용합니다.
        return new StaffAssignmentPolicy();
    }

    @Override
    public String getSpecificInfo() {
        // 결과 예시 화면의 출력 형식을 맞춥니다.
        return "담당 업무: " + staffRole;
    }

    @Override
    public String toFileFormat() {
        // 파일 저장 시 구분자(,)를 사용합니다.
        return String.format("Staff,%s,%s,%d,%s,%s",
                getName(), major, generation, part, staffRole);
    }
}
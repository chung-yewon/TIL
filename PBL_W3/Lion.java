package class3.role;

import class3.policy.AssignmentPolicy;
import class3.policy.LionAssignmentPolicy;

public class Lion extends Role {
    private String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    public AssignmentPolicy getAssignmentPolicy() { return new LionAssignmentPolicy(); }

    @Override
    public String getSpecificInfo() { return "학번: " + studentId; }
}
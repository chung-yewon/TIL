package class3.role;

import class3.policy.AssignmentPolicy;
import class3.policy.StaffAssignmentPolicy;

public class Staff extends Role {
    private String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    @Override
    public AssignmentPolicy getAssignmentPolicy() { return new StaffAssignmentPolicy(); }

    @Override
    public String getSpecificInfo() { return "직책: " + position; }
}
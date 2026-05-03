package class4.policy;

public class StaffAssignmentPolicy implements AssignmentPolicy {
    @Override
    public boolean canSubmit() { return false; } // 운영진은 false!
}

package class3.role;

import class3.policy.AssignmentPolicy;

public abstract class Role {
    private String name, major, part;
    private int generation;

    public Role(String name, String major, int generation, String part) {
        this.name = name; this.major = major; this.generation = generation; this.part = part;
    }

    // 일단은 비워두고 자식들이 채우게 할게요!
    public abstract AssignmentPolicy getAssignmentPolicy();
    public abstract String getSpecificInfo();

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
}
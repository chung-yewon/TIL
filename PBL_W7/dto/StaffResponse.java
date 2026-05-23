package com.example.pbl_w7.dto;

import com.example.pbl_w7.domain.role.Staff;

public class StaffResponse {
    private int generation;
    private String major;
    private String name;
    private String part;
    private final String roleName = "운영진"; // 결과 예시 스펙 고정값
    private String position;

    public StaffResponse(int generation, String major, String name, String part, String position) {
        this.generation = generation;
        this.major = major;
        this.name = name;
        this.part = part;
        this.position = position;
    }

    // 구현 지침 1-3번: 정적 팩토리 메서드 구현
    public static StaffResponse from(Staff staff) {
        return new StaffResponse(
                staff.getGeneration(),
                staff.getMajor(),
                staff.getName(),
                staff.getPart(),
                staff.getPosition()
        );
    }

    public int getGeneration() { return generation; }
    public String getMajor() { return major; }
    public String getName() { return name; }
    public String getPart() { return part; }
    public String getRoleName() { return roleName; }
    public String getPosition() { return position; }
}
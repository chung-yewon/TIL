package com.example.pbl_w7.dto;

import com.example.pbl_w7.domain.role.Lion;

public class LionResponse {
    private int generation;
    private String major;
    private String name;
    private String part;
    private final String roleName = "아기사자"; // 결과 예시 스펙 고정값
    private String studentId;

    public LionResponse(int generation, String major, String name, String part, String studentId) {
        this.generation = generation;
        this.major = major;
        this.name = name;
        this.part = part;
        this.studentId = studentId;
    }

    // 구현 지침 1-3번: 정적 팩토리 메서드 구현
    public static LionResponse from(Lion lion) {
        return new LionResponse(
                lion.getGeneration(),
                lion.getMajor(),
                lion.getName(),
                lion.getPart(),
                lion.getStudentId()
        );
    }

    public int getGeneration() { return generation; }
    public String getMajor() { return major; }
    public String getName() { return name; }
    public String getPart() { return part; }
    public String getRoleName() { return roleName; }
    public String getStudentId() { return studentId; }
}
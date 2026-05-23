package com.example.pbl_w7.domain.role;

public class Lion extends Member {
    private String studentId; // 아기사자 고유 필드

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part); // 부모(Member) 생성자 호출
        this.studentId = studentId;
    }

    // 7주차 구현 지침: Lion 정보 수정을 위한 메서드
    public void updateLionInfo(String major, int generation, String part, String studentId) {
        super.updateCommonInfo(major, generation, part); // 공통 정보 수정
        this.studentId = studentId; // 고유 정보 수정
    }

    public String getStudentId() { return studentId; }
}
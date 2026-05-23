package com.example.pbl_w7.domain.role;

public class Staff extends Member {
    private String position; // 운영진 고유 필드 (부대표, 파트장 등)

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part); // 부모(Member) 생성자 호출
        this.position = position;
    }

    // 7주차 구현 지침: Staff 정보 수정을 위한 메서드
    public void updateStaffInfo(String major, int generation, String part, String position) {
        super.updateCommonInfo(major, generation, part); // 공통 정보 수정
        this.position = position; // 고유 정보 수정
    }

    public String getPosition() { return position; }
}
package com.example.demo.domain;

public class Member {
    private final String role; // 아기사자 or 운영진
    private final String name;
    private final String major;
    private final int generation;
    private final String part;
    private final String studentId;

    public Member(String role, String name, String major, int generation, String part, String studentId) {
        this.role = role;
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("👤 역할: %s\n📌 이름: %s | 🎓 전공: %s | 🔢 기수: %d | 💻 파트: %s\n🆔 학번: %s\n📝 과제 제출 가능: ✅ 가능",
                role, name, major, generation, part, studentId);
    }
}
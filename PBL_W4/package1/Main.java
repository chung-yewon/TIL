package class4.package1;

import class4.role.Lion;
import class4.role.Role;
import class4.role.Staff;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "members.txt"; // 데이터 저장 파일명

    public static void main(String[] args) {
        List<Role> members = loadFromFile(); // 프로그램 시작 시 파일 로드
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= 🦁 멤버 관리 시스템 =========");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 종료 (저장)");
            System.out.print("선택: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("이름: ");
                String name = sc.nextLine();

                boolean isDuplicate = false;
                for (Role member : members) {
                    if (member.getName().equals(name)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (isDuplicate) {
                    System.out.println("❌ 등록 실패: 이미 존재하는 이름입니다.");
                } else {
                    System.out.print("역할 선택 (1: 아기사자, 2: 운영진): ");
                    int roleChoice = sc.nextInt();
                    sc.nextLine();
                    System.out.print("전공: ");
                    String major = sc.nextLine();
                    System.out.print("기수: ");
                    int generation = sc.nextInt();
                    sc.nextLine();
                    System.out.print("파트: ");
                    String part = sc.nextLine();

                    if (roleChoice == 1) {
                        System.out.print("학번: ");
                        String studentId = sc.nextLine();
                        members.add(new Lion(name, major, generation, part, studentId));
                    } else if (roleChoice == 2) {
                        System.out.print("담당 업무: ");
                        String staffRole = sc.nextLine();
                        members.add(new Staff(name, major, generation, part, staffRole));
                    }
                    System.out.println("✅ 등록 완료!");
                    saveToFile(members); // 등록할 때마다 자동 저장
                }
            } else if (choice == 2) {
                if (members.isEmpty()) {
                    System.out.println("등록된 멤버가 없습니다.");
                } else {
                    for (int i = 0; i < members.size(); i++) {
                        System.out.println((i + 1) + ". " + members.get(i).toString());
                    }
                }
            } else if (choice == 3) {
                System.out.print("검색할 이름: ");
                String searchName = sc.nextLine();
                boolean found = false;
                for (Role m : members) {
                    if (m.getName().equals(searchName)) {
                        System.out.println("🔍 검색 결과: " + m.toString());
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("찾을 수 없습니다.");
            } else if (choice == 4) {
                saveToFile(members);
                System.out.println("💾 데이터가 안전하게 저장되었습니다. 종료합니다.");
                break;
            }
        }
    }

    // 파일 저장 로직
    private static void saveToFile(List<Role> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Role m : members) {
                writer.println(m.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("저장 오류: " + e.getMessage());
        }
    }

    // 파일 로드 로직
    private static List<Role> loadFromFile() {
        List<Role> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] d = line.split(",");
                if (d[0].equals("Lion")) {
                    list.add(new Lion(d[1], d[2], Integer.parseInt(d[3]), d[4], d[5]));
                } else if (d[0].equals("Staff")) {
                    list.add(new Staff(d[1], d[2], Integer.parseInt(d[3]), d[4], d[5]));
                }
            }
        } catch (Exception e) {
            System.out.println("로드 오류: " + e.getMessage());
        }
        return list;
    }
}
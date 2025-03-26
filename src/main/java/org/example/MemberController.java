package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    Scanner sc;
    List<MemberJoin> memberJoins = new ArrayList<>();
    int lastMembersId = 3;

    public MemberController(Scanner sc) {
        this.sc = sc;
    }

    public void memberJoin() {

        int id = lastMembersId + 1;


        String regDate = Util.getNowStr();
        System.out.print("이름: ");
        String name = sc.nextLine().trim();

        String loginId = null;

        while (true) {
            System.out.print("아이디: ");
            loginId = sc.nextLine().trim();
            if (joinableId(loginId) == Boolean.parseBoolean(loginId)) {
                System.out.println("이미 등록된 아이디입니다.");
                continue;
            }
            break;
        }
        System.out.println("아이디가 생성되었습니다.");

        String password = null;
        String passwordCheck = null;

        while (true) {
            System.out.print("비밀번호: ");
            password = sc.nextLine().trim();
            System.out.print("비밀번호 확인: ");
            passwordCheck = sc.nextLine().trim();
            if (password.equals(passwordCheck) == false) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }
            break;
        }

        System.out.println("비밀번호가 설정되었습니다.");

        MemberJoin memberJoin = new MemberJoin(id, regDate, loginId, password, name);
        memberJoins.add(memberJoin);
        System.out.printf("%d번 회원이 가입되었습니다.\n", id);
        lastMembersId++;

    }

    private boolean joinableId(String loginId) {
        for (MemberJoin memberJoin : memberJoins) {
            if (memberJoin.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    public void makeMemberData() {
        System.out.println("== 회원 테스트 데이터 생성 ==");

        memberJoins.add(new MemberJoin(1, "2025-01-01", "test1", "test1", "test1"));
        memberJoins.add(new MemberJoin(2, Util.getNowStr(), "test2", "test2", "test2"));
        memberJoins.add(new MemberJoin(3, Util.getNowStr(), "test3", "test3", "test3"));

    }

}

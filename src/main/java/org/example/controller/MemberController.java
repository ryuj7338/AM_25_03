package org.example.controller;


import org.example.dto.MemberJoin;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
    private Scanner sc;
    private List<MemberJoin> memberJoins = new ArrayList<>();
    private String cmd;

    int lastMembersId = 3;

    public MemberController(Scanner sc) {
        this.sc = sc;
    }

    public void doAction(String cmd, String actionMethod) {
        this.cmd = cmd;

        switch (actionMethod) {
            case "join":
                memberJoin();
                break;
            case "login":
                memberLogin();
                break;
            case "logout":
                memberLogout();
                break;
            default:
                System.out.println("Unknown action method");
                break;
        }
    }

    private void memberLogin() {
        if (isLogined()) {
            System.out.println("이미 로그인되었습니다.");
            return;
        }
        System.out.println("== 로그인 ==");
        System.out.println("로그인 아이디: ");
        String loginId = sc.nextLine().trim();
        System.out.println("비밀번호: ");
        String passWord = sc.nextLine().trim();

        MemberJoin memberJoin = getMemberByLoginId(loginId);

        if (memberJoin == null) {
            System.out.println("일치하는 회원이 없습니다.");
            return;
        }
        if (memberJoin.getLoginPw().equals(passWord) ==false) {
            System.out.println("비밀번호를 다시 입력하세요.");
            return;
        }
        loginedMember = memberJoin;

        System.out.printf("%s님 로그인 되었습니다.", loginedMember.getName());
    }

    private void memberLogout() {
        if(!isLogined()) {
            System.out.println("이미 로그아웃 되었습니다.");
            return;
        }
        loginedMember = null;

        System.out.println("로그아웃 되었습니다.");
    }


    private void memberJoin() {

        int id = lastMembersId + 1;


        String regDate = Util.getNowStr();
        System.out.print("이름: ");
        String name = sc.nextLine().trim();

        String loginId = null;

        while (true) {
            System.out.print("로그인 아이디: ");
            loginId = sc.nextLine().trim();
            if (joinableId(loginId) == Boolean.parseBoolean(loginId)) {
                System.out.println("이미 등록된 아이디입니다.");
                continue;
            }
            break;
        }
        System.out.println("아이디가 생성되었습니다.");

        String passWord = null;
        String passWordCheck = null;

        while (true) {
            System.out.print("비밀번호: ");
            passWord = sc.nextLine().trim();
            System.out.print("비밀번호 확인: ");
            passWordCheck = sc.nextLine().trim();
            if (passWord.equals(passWordCheck) == false) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue;
            }
            break;
        }

        System.out.println("비밀번호가 설정되었습니다.");

        MemberJoin memberJoin = new MemberJoin(id, regDate, loginId, passWord, name);
        memberJoins.add(memberJoin);
        System.out.printf("%d번 회원이 가입되었습니다.\n", id);
        lastMembersId++;

    }

    private MemberJoin getMemberByLoginId(String loginId) {
        for (MemberJoin memberJoin : memberJoins) {
            if (memberJoin.getLoginId().equals(loginId)) {
                return memberJoin;
            }
        }
        return null;
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

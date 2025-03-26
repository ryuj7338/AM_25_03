package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {


    public void run() {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 프로그램 시작 ==");


        ArticleController articleController = new ArticleController(sc);
        MemberController memberController = new MemberController(sc);

        articleController.makeTestData();
        memberController.makeMemberData();

        

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요.");
                continue;
            }
            if (cmd.equals("exit")) {
                System.out.println("== 프로그램 종료 ==");
                break;
            }
            if (cmd.equals("member join")) {
                memberController.memberJoin();
            } else if (cmd.equals("article write")) {
                articleController.articleWrite();
            } else if (cmd.startsWith("article list")) {
                articleController.articleList(cmd);
            } else if (cmd.startsWith("article detail")) {
                articleController.articleDetails(cmd);
            } else if (cmd.startsWith("article delete")) {
                articleController.articleDelete(cmd);
            } else if (cmd.startsWith("article modify")) {
                articleController.articleModify(cmd);
            } else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }


    }


}



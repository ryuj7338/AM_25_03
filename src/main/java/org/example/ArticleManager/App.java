package org.example.ArticleManager;

import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;

import java.util.Scanner;

public class App {


    public void run() {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 프로그램 시작 ==");


        ArticleController articleController = new ArticleController(sc);
        MemberController memberController = new MemberController(sc);

        articleController.makeTestData();
        memberController.makeMemberData();

        Controller controller = null;

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요.");
                continue;
            }
            if (cmd.equals("exit")) {

                break;
            }
            
            String[] comBits = cmd.split(" ");
            
            String controllerName = comBits[0];
            
            if(comBits.length == 1) {
                System.out.println("명령어를 확인하세요.");
                continue;
            }
            
            String actionMethodName = comBits[1];
            
            if(controllerName.equals("article")) {
                controller = articleController;
            }else if(controllerName.equals("member")) {
                controller = memberController;
            }else {
                System.out.println("지원하지 않는 기능입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);

            

        }
        System.out.println("== 프로그램 종료 ==");
        sc.close();


    }


}



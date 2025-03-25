package org.example;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 프로그램 시작 ==");

        int lastArticleId = 0;
        List<Article> articles = new ArrayList<>();

        while(true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if(cmd.length() == 0) {
                System.out.println("명령어를 입력하세요.");
                continue;
            }
            if(cmd.equals("exit")) {
                System.out.println("== 프로그램 종료 ==");
                break;
            }

            if (cmd.equals("article write")) {

                int id = lastArticleId + 1;


                System.out.print("제목: ");
                String title = sc.nextLine().trim();
                System.out.print("내용: ");
                String content = sc.nextLine().trim();

                Article article =new Article(id, title, content);
                articles.add(article);

                System.out.println(id+"번 글이 작성되었습니다.");
                lastArticleId++;

            }else if(cmd.equals("article list")) {

                if(articles.size() == 0) {
                    System.out.println("목록이 없습니다.");
                }else {
                    System.out.println("   번호   /   제목   /   내용   ");
                    for(int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);
                        System.out.printf("   %d   /   %s   /   %s\n",article.getId(),article.getTitle(),article.getContent());
                    }
                }
            }else if(cmd.startsWith("article detail")) {

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;



                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }
                if (foundArticle == null) {
                    System.out.printf("%d번 게시글은 없습니다.", id);
                    continue;
                }
                System.out.println("번호: " + foundArticle.getId());
                System.out.println("날짜: " +);
                System.out.println("제목: " + foundArticle.getTitle());
                System.out.println("내용: " + foundArticle.getContent());
            }else if(cmd.startsWith("article delete")) {

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }
                if (foundArticle == null) {
                    System.out.printf("%d번 게시글은 없습니다.", id);
                    continue;
                }
                articles.remove(foundArticle);
                System.out.printf("%d번 게시글이 삭제되었습니다.",id);
            } else if(cmd.startsWith("article modify")) {

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }
                if (foundArticle == null) {
                    System.out.printf("%d번 게시글은 없습니다.", id);
                    continue;
                }
                System.out.println("기존 제목: " + foundArticle.getId());
                System.out.println("기존 내용: " + foundArticle.getTitle());
                System.out.print("새 제목: ");
                String newTitle = sc.nextLine().trim();
                System.out.print("새 내용: ");
                String newContent = sc.nextLine().trim();

                foundArticle.setTitle(newTitle);
                foundArticle.setContent(newContent);

                System.out.printf("%d번 게시글이 수정되었습니다.\n",id);
            }else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }


    }
}


class Article {
    private int id;
    private String title;
    private String content;

    public Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
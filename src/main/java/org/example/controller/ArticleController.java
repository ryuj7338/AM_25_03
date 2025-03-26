package org.example.controller;

import org.example.dto.Article;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    private Scanner sc;
    private String cmd;
    int lastArticleId = 3;
    private List<Article> articles = new ArrayList<>();

    public ArticleController(Scanner sc) {
        this.sc = sc;
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "write":
                articleWrite();
                break;
            case "list":
                articleList();
                break;
            case "detail":
                articleDetail();
                break;
            case "delete":
                articleDelete();
                break;
            case "modify":
                articleModify();
                break;
            default:
                System.out.println("Unknown action method");
                break;
        }
    }

    private void articleWrite() {
        System.out.println("== 게시글 작성 ==");
        int id = lastArticleId + 1;
        String regDate = Util.getNowStr();
        String updateDate = Util.getNowStr();
        System.out.print("제목: ");
        String title = sc.nextLine().trim();
        System.out.print("내용: ");
        String content = sc.nextLine().trim();

        Article article = new Article(id, regDate, updateDate, title, content);
        articles.add(article);

        System.out.println(id + "번 글이 작성되었습니다.");
        lastArticleId++;

    }

    private void articleList() {
        System.out.println("== 게시글 목록 ==");
        if (articles.size() == 0) {
            System.out.println("목록이 없습니다.");
            return;
        }

        String searchKeyword = cmd.substring("article list".length()).trim();

        List<Article> foundTitle = articles;

        if (searchKeyword.length() > 0) {
            System.out.println("검색어: " + searchKeyword);
            foundTitle = new ArrayList<>();

            for (Article article : articles) {
                if (article.getTitle().contains(searchKeyword)) {
                    foundTitle.add(article);
                }
            }

        }
        if (foundTitle.size() == 0) {
            System.out.println("검색 결과가 없습니다.");
            return;
        }

        System.out.println("   번호   /   날짜   /   제목   /   내용   ");
        for (int i = foundTitle.size() - 1; i >= 0; i--) {
            Article article = foundTitle.get(i);
            if (Util.getNowStr().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                System.out.printf("   %d   /   %s   /   %s   /   %s   \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getContent());

            } else {
                System.out.printf("   %d   /   %s   /   %s   /   %s   \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getContent());

            }
        }
    }

    private void articleDetail() {
        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticle(id);


        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 없습니다.", id);
            return;
        }
        System.out.println("번호: " + foundArticle.getId());
        System.out.println("작성 날짜: " + foundArticle.getRegDate());
        System.out.println("수정 날짜: " + foundArticle.getUpdateDate());
        System.out.println("제목: " + foundArticle.getTitle());
        System.out.println("내용: " + foundArticle.getContent());
    }

    private void articleDelete() {
        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticle(id);


        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 없습니다.", id);
            return;
        }
        articles.remove(foundArticle);
        System.out.printf("%d번 게시글이 삭제되었습니다.", id);
    }

    private void articleModify() {
        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticle(id);


        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 없습니다.", id);
            return;
        }
        System.out.println("기존 제목: " + foundArticle.getId());
        System.out.println("기존 내용: " + foundArticle.getTitle());
        System.out.print("새 제목: ");
        String newTitle = sc.nextLine().trim();
        System.out.print("새 내용: ");
        String newContent = sc.nextLine().trim();

        foundArticle.setUpdateDate(Util.getNowStr());
        foundArticle.setTitle(newTitle);
        foundArticle.setContent(newContent);

        System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
    }


    private Article getArticle(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;

            }
        }
        return null;
    }


    /**
     * 데이터 테스트 함수 생성
     **/
    public void makeTestData() {
        System.out.println("== 테스트 데이터 생성 ==");

        articles.add(new Article(1, "2025-01-01", "2025-03-20", "제목1", "내용 1"));
        articles.add(new Article(2, Util.getNowStr(), Util.getNowStr(), "제목2", "내용 2"));
        articles.add(new Article(3, Util.getNowStr(), Util.getNowStr(), "제목3", "내용 3"));
    }

}

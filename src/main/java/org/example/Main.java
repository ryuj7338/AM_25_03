package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static List<Article> articles = new ArrayList<>();
    static List<Join> joins = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 프로그램 시작 ==");


        int lastArticleId = 3;
        int lastMembersId = 0;

        makeTestData();

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

            if (cmd.equals("article write")) {

                int id = lastArticleId + 1;
//                LocalTime now = LocalTime.now();
//                String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                String regDate = formatedNow;
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

            } else if (cmd.startsWith("article list")) {

                if (articles.size() == 0) {
                    System.out.println("목록이 없습니다.");
                    continue;
                }
                String searchKeyword = cmd.split(" ")[2];

                List<Article> foundTitle = articles;

                if (searchKeyword.length() > 0) {

                    foundTitle = new ArrayList<>();

                    for (Article article : articles) {
                        if (article.getTitle().contains(searchKeyword)) {
                            foundTitle.add(article);
                        }
                    }

                }  if (searchKeyword.length() == 0) {
                    System.out.println("검색 결과가 없습니다.");
                    continue;
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

            } else if (cmd.startsWith("article detail")) {


                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticle(id);


                if (foundArticle == null) {
                    System.out.printf("%d번 게시글은 없습니다.", id);
                    continue;
                }
                System.out.println("번호: " + foundArticle.getId());
                System.out.println("작성 날짜: " + foundArticle.getRegDate());
                System.out.println("수정 날짜: " + foundArticle.getUpdateDate());
                System.out.println("제목: " + foundArticle.getTitle());
                System.out.println("내용: " + foundArticle.getContent());
            } else if (cmd.startsWith("article delete")) {

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticle(id);


                if (foundArticle == null) {
                    System.out.printf("%d번 게시글은 없습니다.", id);
                    continue;
                }
                articles.remove(foundArticle);
                System.out.printf("%d번 게시글이 삭제되었습니다.", id);
            } else if (cmd.startsWith("article modify")) {

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticle(id);


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

                foundArticle.setUpdateDate(Util.getNowStr());
                foundArticle.setTitle(newTitle);
                foundArticle.setContent(newContent);

                System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
            }else if (cmd.equals("member join")) {

                int id = lastMembersId + 1;



                String regDate = Util.getNowStr();
                System.out.print("이름: ");
                String name = sc.nextLine().trim();
                System.out.print("아이디: ");
                String loginId = sc.nextLine().trim();


                System.out.println("아이디가 생성되었습니다.");
                System.out.print("비밀번호: ");
                String loginPw = sc.nextLine().trim();
                System.out.println("비밀번호가 설정되었습니다.");

                Join join = new Join(id, regDate, loginId, loginPw, name);
                joins.add(join);
                System.out.print("%d번 회원이 가입되었습니다.\n");
                lastMembersId++;




            }
            else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }


    }

    private static Article getArticle(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;

            }
        }
        return null;
    }


    /**데이터 테스트 함수 생성**/
    public static void makeTestData() {
        System.out.println("== 테스트 데이터 생성 ==");

        articles.add(new Article(1, "2025-01-01", "2025-03-20", "제목1", "내용 1"));
        articles.add(new Article(2, Util.getNowStr(), Util.getNowStr(), "제목2", "내용 2"));
        articles.add(new Article(3, Util.getNowStr(), Util.getNowStr(), "제목3", "내용 3"));
    }


}
class Join  {
    int id;
    String regDate;
    String loginId;
    String loginPw;
    String name;

    public Join(int id, String regDate, String loginId, String loginPw, String name) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Article {
    private int id;
    private String regDate;
    private String updateDate;
    private String title;
    private String content;

    public Article(int id, String regDate, String updateDate, String title, String content) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.title = title;
        this.content = content;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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
package com.app;

import com.config.SessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SqlSession session = SessionFactory.getSession();

        ArticleDao articleMysql = session.getMapper(ArticleDao.class);

        Scanner sc = new Scanner(System.in);
        boolean switchStatus = true;

        System.out.println("프로그램을 시작합니다!");

        while ( switchStatus ) {

            try {
                System.out.print("명령어를 입력해 주세요 : ");
                String command = sc.nextLine();

                switch (command) {

                    case "stop":
                        System.out.println("프로그램을 종료합니다.");
                        switchStatus = false;
                        break;

                    case "write":
                        Article article = new Article();

                        System.out.print("제목을 입력해주세요 : ");
                        String title = sc.nextLine();
                        article.setTitle(title);

                        System.out.print("내용을 입력해주세요 : ");
                        String body = sc.nextLine();
                        article.setBody(body);

                        articleMysql.write(article);
                        System.out.println("성공적으로 글이 작성되었습니다!");
                        session.commit();
                        break;

                    case "modify":
                        System.out.print("수정하고자 하는 글 번호를 입력해주세요 : ");
                        String modifyArticleId = sc.nextLine();

                        Article modifyArticle = articleMysql.findById(Integer.parseInt(modifyArticleId));

                        if ( isNull(modifyArticle) ) {
                            break;
                        }

                        System.out.println(":: 선택하신 글 :: ");
                        System.out.println(modifyArticle);

                        System.out.print("새로운 제목 입력 : ");
                        String updateTitle = sc.nextLine();
                        modifyArticle.setTitle(updateTitle);

                        System.out.print("새로운 내용 입력 : ");
                        String updateBody = sc.nextLine();
                        modifyArticle.setBody(updateBody);

                        articleMysql.modify(modifyArticle);
                        session.commit();

                        System.out.println("변경이 완료 되었습니다!");
                        break;

                    case "delete":
                        System.out.print("삭제하고자 하는 글 번호를 입력해 주세요 : ");
                        String deleteArticleId = sc.nextLine();

                        if ( isNull(articleMysql.findById(Integer.parseInt(deleteArticleId))) ) {
                            break;
                        }

                        articleMysql.delete(Integer.parseInt(deleteArticleId));
                        session.commit();

                        System.out.println(deleteArticleId + "번 글이 삭제되었습니다!");
                        break;

                    default:
                        break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("잘못된 입력입니다.");
            }
        }

    }

    public static boolean isNull(Object o) {
        if ( o == null ) {
            System.out.println("잘못된 입력입니다.");
            return true;
        } else {
            return false;
        }
    }

}

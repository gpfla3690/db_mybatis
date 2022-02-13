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

                    default:
                        break;
                }
            }catch (Exception e){
                System.out.println("잘못된 입력입니다.");
            }
        }

    }

}

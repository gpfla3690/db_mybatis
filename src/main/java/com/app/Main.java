package com.app;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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

                    default:
                        break;
                }
            }catch (Exception e){
                System.out.println("잘못된 입력입니다.");
            }
        }

    }

}

package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static ArrayList<Quote> quotes;
    static int count;
    Scanner sc;

    App() {
        quotes = new ArrayList<>();
        count = 0;
        sc = new Scanner(System.in);
    }

    void run() {

        System.out.println("== 명언 앱 ==");
        while(true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            }
           else if( cmd.equals("등록")) {

               actionWrite();
            }
           else if(cmd.equals("목록")) {
                actionList();
           }

           else if (cmd.startsWith("삭제")) {
               actionDelete(cmd);
           }

           else if (cmd.startsWith("수정")) {
              actionModify(cmd);
            }
        }
    }

    void actionWrite() {

        count ++;
        System.out.print("명언 : ");
        String comment  = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        Quote quote = new Quote(count,comment,author);
        quotes.add(quote);
        System.out.println(quote.id +"번 명언이 등록되었습니다.");
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = quotes.size() - 1; i >= 0; i--) {
            Quote quote = quotes.get(i);
            System.out.println(quote.id + " / " + quote.author + " / " + quote.comment);
        }

    }

    void actionDelete(String cmd){

        boolean found = false;

        int deleteId = getParamAsInt(cmd,"id",0);

        if (deleteId == 0) {
            System.out.println("id를 정확하게 입력해주세요.");
            return;
        }
        for (int i = 0; i< quotes.size(); i++) {
            Quote quote = quotes.get(i);
            if(quote.id == deleteId) {
                quotes.remove(i);
                System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                found = true;
                break;
            }
        }

        if (!found){
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }


    void actionModify(String cmd) {
        int modifyId = getParamAsInt(cmd,"id",0);
        if(modifyId == 0) {
            System.out.println("id를 정확하게 입력해주세요.");
        }
        Quote quote = null;
        for(Quote q : quotes) {
            if(q.id == modifyId) {
                quote = q;
                break;
            }
        }
        String prevComment = quote.comment;
        String prevAuthor = quote.author;

        System.out.println("명언(기존) : " + prevComment);
        System.out.print("명언 : ");
        String modiComment = sc.nextLine();
        System.out.println("작가(기존) : " + prevAuthor);
        System.out.print("작가 : ");
        String modiAuthor = sc.nextLine();
        quote.comment = modiComment;
        quote.author  = modiAuthor;


    }

    int getParamAsInt(String cmd, String paramName, int defaultValue){

        String[] cmdBits = cmd.split("\\?",2);
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");


        for(int i =0; i<queryStringBits.length;i++){
            String queryParamStr = queryStringBits[i];

            String [] queryParamStrBits = queryParamStr.split("=",2);

            String _paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if (_paramName.equals(paramName)) {
                try{   // 문제 x
                    return Integer.parseInt(paramValue);
                }
                catch (NumberFormatException e) {  // 문제 O
                    return defaultValue;
                }
            }
        }

        return defaultValue;


    }
}

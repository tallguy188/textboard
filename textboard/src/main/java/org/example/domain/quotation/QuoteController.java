package org.example.domain.quotation;

import org.example.base.Rq;

import java.util.ArrayList;
import java.util.Scanner;

public class QuoteController {
    private static ArrayList<Quote> quotes;
    private static int count;

    private Scanner sc;
    public QuoteController(Scanner scanner) {
        this.sc = scanner;
        count = 0;
        quotes = new ArrayList<>();

        initTestData();
    }

    private void initTestData() {
        for (int i=0; i<10;i++) {
            write("명언 " + i,"작가 " + i);

        }
    }


    public void actionWrite() {


        System.out.print("명언 : ");
        String comment  = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        Quote quote = write(comment,author);
        System.out.println(quote.getId() +"번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = quotes.size() - 1; i >= 0; i--) {
            Quote quote = quotes.get(i);
            System.out.println(quote.getId() + " / " + quote.getAuthor() + " / " + quote.getComment());
        }

    }

    public void actionDelete(Rq rq){

        boolean found = false;

        int deleteId = rq.getParamAsInt("id",0);

        if (deleteId == 0) {
            System.out.println("id를 정확하게 입력해주세요.");
            return;
        }
        for (int i = 0; i< quotes.size(); i++) {
            Quote quote = quotes.get(i);
            if(quote.getId() == deleteId) {
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


    public void actionModify(Rq rq) {
        int modifyId = rq.getParamAsInt("id",0);
        if(modifyId == 0) {
            System.out.println("id를 정확하게 입력해주세요.");
            return;
        }
        Quote quote = null;
        for(Quote q : quotes) {
            if(q.getId() == modifyId) {
                quote = q;
                break;
            }
        }
        String prevComment = quote.getComment();
        String prevAuthor = quote.getAuthor();

        System.out.println("명언(기존) : " + prevComment);
        System.out.print("명언 : ");
        String modiComment = sc.nextLine();
        System.out.println("작가(기존) : " + prevAuthor);
        System.out.print("작가 : ");
        String modiAuthor = sc.nextLine();
        quote.setComment(modiComment);
        quote.setAuthor(modiAuthor);


    }


    private Quote write(String comment, String author) {
        count ++;

        Quote quote = new Quote(count,comment,author);
        quotes.add(quote);

        return quote;
    }

}

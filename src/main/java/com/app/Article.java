package com.app;

import java.time.LocalDateTime;

public class Article {

    public int id;
    public String title;
    public String body;
    public LocalDateTime regDate;
    public LocalDateTime updateDate;

    @Override
    public String toString() {
        return "번호 : " + id + "\r\n" + "제목 : " + title + "\r\n" + "내용 : " + body + "\r\n" + "생성일 : " + regDate + "\r\n" + "수정일 : " + updateDate;
    }

}

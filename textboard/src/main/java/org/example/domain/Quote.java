package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor  // 모든 필드를 입력받는 생성자를 대체함.
public class Quote {

    @Getter
    private  int id;

    @Getter
    @Setter
    private String comment;

    @Getter
    @Setter
    private String author;
}

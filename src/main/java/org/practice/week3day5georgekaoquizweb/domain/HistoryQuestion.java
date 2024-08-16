package org.practice.week3day5georgekaoquizweb.domain;

import lombok.*;

@Getter
@Setter
@ToString
public abstract class HistoryQuestion {
    private int historyquiz_id;
    private int question_id;
    private String user_answer;
}

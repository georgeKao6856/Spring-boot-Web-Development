package org.practice.week3day5georgekaoquizweb.domain;

import lombok.*;

@Getter
@Setter
@ToString
public abstract class HistoryQuizJoin {
    private int historyquiz_id;
    private int quiz_id;
    private String quiz_name;
    private String quiz_date;
}

package org.practice.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.HistoryQuiz;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HistoryQuizJdbc extends HistoryQuiz {
    private int historyquiz_id;
    private int user_id;
    private int quiz_id;
    private boolean ongoing;
    private String quiz_date;
    private String quiz_end_time;
    private int score;
}

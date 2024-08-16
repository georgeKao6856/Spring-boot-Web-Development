package org.practice.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.HistoryQuestion;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HistoryQuestionJdbc extends HistoryQuestion {
    private int historyquiz_id;
    private int question_id;
    private String user_answer;
}

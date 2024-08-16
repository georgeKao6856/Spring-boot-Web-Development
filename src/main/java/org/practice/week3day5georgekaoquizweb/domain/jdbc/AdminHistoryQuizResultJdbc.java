package org.practice.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.AdminHistoryQuizResult;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminHistoryQuizResultJdbc extends AdminHistoryQuizResult {
    private int historyquiz_id;
    private int user_id;
    private String username;
    private String firstname;
    private String lastname;
    private int quiz_id;
    private String quiz_name;
    private int category_id;
    private String category_name;
    private String quiz_date;
    private String quiz_end_time;
    private int score;
}

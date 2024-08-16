package org.practice.week3day5georgekaoquizweb.domain.hibernate;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryQuizJoinHibernate {
    private int historyquiz_id;
    private int quiz_id;
    private String quiz_name;
    private String quiz_date;
}

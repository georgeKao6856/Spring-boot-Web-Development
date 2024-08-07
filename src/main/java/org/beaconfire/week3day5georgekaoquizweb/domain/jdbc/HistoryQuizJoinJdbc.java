package org.beaconfire.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.HistoryQuizJoin;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HistoryQuizJoinJdbc extends HistoryQuizJoin {
    private int historyquiz_id;
    private int quiz_id;
    private String quiz_name;
    private String quiz_date;
}

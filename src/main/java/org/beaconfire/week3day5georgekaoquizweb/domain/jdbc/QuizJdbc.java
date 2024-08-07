package org.beaconfire.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.Quiz;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuizJdbc extends Quiz {
    private int quiz_id;
    private String quiz_name;
    private int category_id;
}

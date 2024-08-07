package org.beaconfire.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.QuestionOption;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionJdbc extends QuestionOption {
    private int option_id;
    private int question_id;
    private String content;
}

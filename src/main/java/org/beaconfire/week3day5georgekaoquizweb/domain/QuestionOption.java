package org.beaconfire.week3day5georgekaoquizweb.domain;

import lombok.*;

@Getter
@Setter
@ToString
public abstract class QuestionOption {
    private int option_id;
    private int question_id;
    private String content;
}

package org.practice.week3day5georgekaoquizweb.domain;

import lombok.*;

@Getter
@Setter
@ToString
public abstract class Question {
    private int question_id;
    private String question_subject;
    private String question_description;
    private int category_id;
    private String answer;
    private boolean active;
}

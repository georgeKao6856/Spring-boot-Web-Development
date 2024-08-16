package org.practice.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.Question;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionJdbc extends Question {
    private int question_id;
    private String question_subject;
    private String question_description;
    private int category_id;
    private String answer;
    private boolean active;
}

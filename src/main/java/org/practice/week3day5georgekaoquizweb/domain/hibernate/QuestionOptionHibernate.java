package org.practice.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.QuestionOption;

@Entity
@Table(name="QuestionOption")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionHibernate extends QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int option_id;

//    @JoinColumn(nullable = false)
//    private int question_id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionHibernate question;
}

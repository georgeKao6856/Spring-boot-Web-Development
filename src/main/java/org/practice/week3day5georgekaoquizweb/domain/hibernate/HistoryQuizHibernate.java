package org.practice.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.HistoryQuiz;

@Entity
@Table(name="historyQuiz")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryQuizHibernate extends HistoryQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int historyquiz_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserHibernate user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizHibernate quiz;

    @Column
    private boolean ongoing;

    @Column(insertable = false)
    private String quiz_date;

    @Column
    private String quiz_end_time;

    @Column(insertable = false)
    private int score;
}

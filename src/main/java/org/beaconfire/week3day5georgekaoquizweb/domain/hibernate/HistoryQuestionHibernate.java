package org.beaconfire.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.HistoryQuestion;

@Entity
@Table(name="historyQuestion")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryQuestionHibernate extends HistoryQuestion {

    @EmbeddedId
    private HistoryQuestionId id;

    @ManyToOne
    @MapsId("historyquiz_id")
    @JoinColumn(name = "historyquiz_id", insertable = false, updatable = false)
    private HistoryQuizHibernate historyQuiz;

    @ManyToOne
    @MapsId("question_id")
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private QuestionHibernate question;

    @Column(name = "user_answer")
    private String user_answer;
}

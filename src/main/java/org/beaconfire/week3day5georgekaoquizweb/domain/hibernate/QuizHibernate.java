package org.beaconfire.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.Quiz;

import java.util.List;

@Entity
@Table(name="Quiz")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizHibernate extends Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int quiz_id;

    @Column(nullable = false)
    private String quiz_name;

    @OneToMany(mappedBy = "quiz")
    @ToString.Exclude
    private List<HistoryQuizHibernate> historyQuizzes;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryHibernate category;
}

package org.beaconfire.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.Question;
import org.beaconfire.week3day5georgekaoquizweb.domain.QuestionOption;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Question")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionHibernate extends Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int question_id;

    @Column(nullable = false)
    private String question_subject;

    @Column(nullable = false)
    private String question_description;

    @Column(nullable = false)
    private int category_id;

    @Column(nullable = false)
    private String answer;

    @Column(insertable = false, name="is_active")
    private boolean active;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<QuestionOptionHibernate> options = new ArrayList<>();

    public void addOption(QuestionOptionHibernate option) {
        this.options.add(option);
        option.setQuestion(this);
    }
}

package org.beaconfire.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class HistoryQuestionId implements Serializable {

    @Column(name = "historyquiz_id")
    private int historyquiz_id;

    @Column(name = "question_id")
    private int question_id;
}


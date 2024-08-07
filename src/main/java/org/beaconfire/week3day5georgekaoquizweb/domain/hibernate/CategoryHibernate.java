package org.beaconfire.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.beaconfire.week3day5georgekaoquizweb.domain.Category;

import java.util.List;

@Entity
@Table(name="Category")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryHibernate extends Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int category_id;

    @Column(nullable = false)
    private String category_name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<QuizHibernate> quizzes;
}

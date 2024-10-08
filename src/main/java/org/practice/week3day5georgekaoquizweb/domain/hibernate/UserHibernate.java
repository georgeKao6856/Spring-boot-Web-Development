package org.practice.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.User;

import java.util.List;

@Entity
@Table(name="Users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHibernate extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int user_id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "is_admin", insertable = false)
    private boolean admin;

    @Column(nullable = false, name = "is_active", insertable = false)
    private boolean active;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<HistoryQuizHibernate> historyQuizzes;
}

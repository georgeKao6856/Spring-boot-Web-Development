package org.practice.week3day5georgekaoquizweb.domain.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.ContactUs;

@Entity
@Table(name="ContactUsMessage")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactUsHibernate extends ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int contactus_id;

    @Column(nullable = false)
    private String msg_subject;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String message;

    @Column(insertable = false)
    private String submit_time;

}

package org.beaconfire.week3day5georgekaoquizweb.domain;

import lombok.*;

@Getter
@Setter
@ToString
public abstract class Quiz {
    private int quiz_id;
    private String quiz_name;
    private int category_id;
}

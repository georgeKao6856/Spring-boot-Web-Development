package org.practice.week3day5georgekaoquizweb.domain.jdbc;

import lombok.*;
import org.practice.week3day5georgekaoquizweb.domain.Category;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryJdbc extends Category {
    private int category_id;
    private String category_name;
}

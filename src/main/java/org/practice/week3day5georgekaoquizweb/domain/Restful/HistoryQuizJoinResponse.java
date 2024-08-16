package org.practice.week3day5georgekaoquizweb.domain.Restful;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.practice.week3day5georgekaoquizweb.domain.HistoryQuizJoin;

import java.util.List;

@Getter
@Setter
@Builder
public class HistoryQuizJoinResponse {
    private ResponseStatus status;
    private List<HistoryQuizJoin> historyQuizJoinList;
}

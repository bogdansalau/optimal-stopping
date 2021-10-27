package com.bogdansalau.optimal.player;

import com.bogdansalau.optimal.model.Candidate;

import java.util.List;

public interface Player {

    boolean pick(List<Candidate> pastCandidates, Candidate currentCandidate, int totalCandidates);

    default String getName() {
        return this.getClass().getSimpleName();
    }

}

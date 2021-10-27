package com.bogdansalau.optimal.player.impl;

import com.bogdansalau.optimal.model.Candidate;
import com.bogdansalau.optimal.player.Player;

import java.util.List;

public class Player1 implements Player {

    @Override
    public boolean pick(List<Candidate> pastCandidates, Candidate currentCandidate, int totalCandidates) {
        return true;
    }

}

package com.bogdansalau.optimal.model;

public class PlayerScore {

    String playerName;

    int guessed;

    int missed;

    public PlayerScore(String playerName) {
        this.playerName = playerName;
    }

    public float getSuccessRate() {
        if (missed == 0) return 0;
        return ((float) guessed) / ((float) ( missed + guessed));
    }

    public void miss() {
        missed++;
    }

    public void hit() {
        guessed++;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGuessed() {
        return guessed;
    }
}

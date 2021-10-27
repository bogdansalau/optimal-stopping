package com.bogdansalau.optimal.game;

import com.bogdansalau.optimal.model.Candidate;
import com.bogdansalau.optimal.model.PlayerScore;
import com.bogdansalau.optimal.player.Player;
import com.bogdansalau.optimal.ui.TableController;
import javafx.application.Platform;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.bogdansalau.optimal.game.Constants.*;
import static com.bogdansalau.optimal.ui.TableController.DELAY_BETWEEN_GAMES;

public class GameController {

    private final TableController tableController;

    private final Random random = new Random();

    private final Map<Player, PlayerScore> playerScores = new HashMap<>();

    public GameController(List<Player> players, TableController tableController) {
        this.tableController = tableController;
        players.forEach(p -> playerScores.put(p, new PlayerScore(p.getName())));
    }

    public void start() {
        IntStream
                .range(0, GAMES_PER_ROUND)
                .forEach(g -> playGame());
    }

    private void playGame() {
        List<Candidate> candidates = generateCandidates();

        playerScores.keySet().forEach(p -> {
            computeScore(p, candidates);
            try {
                Thread.sleep(((int) DELAY_BETWEEN_GAMES));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> tableController.update(playerScores));
        });
    }

    private void computeScore(Player p, List<Candidate> candidates) {
        Candidate bestCandidate = getBestCandidate(candidates);

        List<Candidate> pastCandidates = new ArrayList<>();

        int i = 0;
        Candidate nextCandidate = candidates.get(i);

        boolean candidatePicked = true;
        while(!p.pick(pastCandidates, nextCandidate, candidates.size())) {
            pastCandidates.add(nextCandidate);

            if(i == candidates.size() - 1) {
                candidatePicked = false;
                break;
            } else {
                i++;
            }
            nextCandidate = candidates.get(i);
        }

        if(candidatePicked && nextCandidate.getPerformance() == bestCandidate.getPerformance()) {
            playerScores.get(p).hit();
            return;
        }

        playerScores.get(p).miss();
    }

    private Candidate getBestCandidate(List<Candidate> candidates) {
        return candidates.stream().reduce(new Candidate(0), (a, b) -> a.getPerformance() > b.getPerformance() ? a : b);
    }

    private List<Candidate> generateCandidates() {
        int min = random.nextInt(1_000_000);

        List<Candidate> result = IntStream
                .range(0, CANDIDATES)
                .map(i -> i + min + random.nextInt(20))
                .mapToObj(Candidate::new)
                .collect(Collectors.toList());

        Collections.shuffle(result);

        return result;
    }

}

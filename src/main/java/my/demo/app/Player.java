package my.demo.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private String name;
    private List<Card> drawnCards;
    private boolean hold;

    private Result result;

    public Player(String name) {
        this.name = name;
        drawnCards = new ArrayList<>();
    }

    public Player(Player player) {
        name = player.name;
        drawnCards = new ArrayList<>();
        drawnCards.addAll(player.drawnCards);
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void addCard(Card card) {
        drawnCards.add(card);
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return Integer.toString(score());
    }

    public String getWinner(){
        if (this.result != null) {
            return this.result.getWinner();
        } else {
            return "-";
        }
    }

    public int score() {
        return drawnCards.stream().mapToInt(card -> card.value.getGameValue()).sum();
    }

    public String getDrawnCards() {
        return drawnCards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return name + ": " + getDrawnCards();
    }

    public void clear() {
        drawnCards = new ArrayList<>();
        result = null;
    }

    public void hold() {
        this.hold = hold;
    }

    public boolean hasHolded() {
        return this.hold;
    }
}

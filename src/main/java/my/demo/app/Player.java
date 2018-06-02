package my.demo.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private String name;
    private List<Card> drawnCards;
    private boolean hold;

    public Player(String name) {
        this.name = name;
        drawnCards = new ArrayList<>();
    }

    public Player(Player player) {
        name = player.name;
        drawnCards = new ArrayList<>();
        drawnCards.addAll(player.drawnCards);
    }

    public void addCard(Card card) {
        drawnCards.add(card);
    }

    public int score() {
        return drawnCards.stream().mapToInt(card -> card.value.getGameValue()).sum();
    }

    public String getName() {
        return name;
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
    }

    public void hold() {
        this.hold = hold;
    }

    public boolean hasHolded() {
        return this.hold;
    }
}

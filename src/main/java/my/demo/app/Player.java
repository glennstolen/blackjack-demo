package my.demo.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private String name;

    private List<Card> drawnCards;

    public Player(String name) {
        this.name = name;
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

    public String getCards() {
        return drawnCards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return name + ": " + getCards();
    }

    public void clear() {
        drawnCards = new ArrayList<>();
    }
}

package my.demo.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private String name;

    private List<Card> drawnCards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        drawnCards.add(card);
    }

    public int score() {
        return drawnCards.stream().mapToInt(card -> card.value).sum();
    }

    public String getName() {
        return name;
    }

    public String getCards() {
        return drawnCards.stream()
                .map(card -> card.suite +card.value)
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

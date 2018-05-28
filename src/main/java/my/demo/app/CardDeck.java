package my.demo.app;

import java.util.*;

public class CardDeck {

    private enum Suite {
        H, D, C, S
    }

    private enum CardValue {
        TWO("2",2),
        THREE("3",3),
        FOUR("4",4),
        FIVE("5",5),
        SIX("6",6),
        SEVEN("7",7),
        EIGHT("8",8),
        NINE("9", 9),
        TEN("10",10),
        JACK("J",10),
        QUEEN("Q",10),
        KING("K",10),
        ACE("A",11);

        private String rank;
        private int value;

        CardValue(String rank, int value) {
            this.rank = rank;
            this.value = value;
        }

        int getValue() {
            return value;
        }

        public String getRank() {
            return rank;
        }
    }

    private List<Card> cards;

    private ArrayDeque<Card> deck;

    CardDeck() {
        cards = new ArrayList<>();
        for (Suite suite : Suite.values()) {
            for (CardValue cardValue : CardValue.values()) {
                cards.add(new Card(suite.name(), cardValue.getRank(), cardValue.getValue()));
            }
        }
        shuffle();
    }

    CardDeck(List<Card> cards) {
        this.cards = cards;
        shuffle();
    }


    void shuffle() {
        Collections.shuffle(cards);
        deck = new ArrayDeque(cards);
    }

    Card draw() {
        return deck.pop();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardDeck cardDeck = (CardDeck) o;
        return Arrays.equals(cardDeck.deck.toArray(),deck.toArray());
    }

    @Override
    public int hashCode() {
        return deck.hashCode();
    }

    /** For tests only */
    int size() {
        return cards.size();
    }

    Card peek() {
        return deck.peek();
    }

    static CardDeck clone(CardDeck cardDeck) {
        ArrayDeque<Card> deckCopy = cardDeck.deck.clone();
        return new CardDeck(cardDeck.cards, deckCopy);
    }

    private CardDeck(List<Card> cards, ArrayDeque<Card> deck) {
        this.deck = deck;
        this.cards = cards;
    }
}

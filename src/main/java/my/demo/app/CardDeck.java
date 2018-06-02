package my.demo.app;

import java.util.*;

public class CardDeck {

    private List<Card> cards;

    private ArrayDeque<Card> deck;

    public CardDeck() {
        cards = new ArrayList<>();
        for (Card.Suite suite : Card.Suite.values()) {
            for (Card.CardValue cardValue : Card.CardValue.values()) {
                cards.add(new Card(suite, cardValue));
            }
        }
        shuffle();
    }

    public CardDeck(List<Card> cards) {
        this.cards = cards;
        shuffle();
    }


    public void shuffle() {
        Collections.shuffle(cards);
        deck = new ArrayDeque(cards);
    }

    public Card draw() {
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

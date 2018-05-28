package my.demo.app;


import my.demo.utils.ResourceUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CardDeckTest {

    @Test
    public void initialize_without_input() {
        CardDeck deck = new CardDeck();
        assertEquals(52, deck.size());
    }

    @Test
    public void initialize_with_json_file() {
        List<Card> cards = ResourceUtils.getCardsFromJsonFile("card-deck.json");
        CardDeck deck = new CardDeck(cards);
        assertEquals(26, deck.size());
    }

    @Test
    public void initialize_with_token_file() {
        List<Card> cards = ResourceUtils.getCardsFromTokenizedFile("card-deck.txt");
        CardDeck deck = new CardDeck(cards);
        assertEquals(40, deck.size());
    }

    @Test
    public void shuffle_card_deck() {
        CardDeck deck = new CardDeck();
        CardDeck clonedDeck = CardDeck.clone(deck);
        assertEquals(deck, clonedDeck);
        deck.shuffle();
        assertNotEquals(deck, clonedDeck);
    }

    @Test
    public void draw_from_card_deck() {
        CardDeck deck = new CardDeck();
        Card peek = deck.peek();

        Card card = deck.draw();

        assertEquals(peek, card);
        System.out.println("Drawn card: " + card);

    }

}
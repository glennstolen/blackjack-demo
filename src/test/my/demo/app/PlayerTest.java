package my.demo.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void test_draw_card() {
        Player player = new Player("test");

        player.addCard(new Card(Card.Suite.valueOf("S"),Card.CardValue.getCardValue("A")));

        assertEquals(11, player.score());
    }

    @Test
    void test_score() {
        int expectedSum = 0;

        Player player = new Player("test");

        for (int i = 0; i < 10; i++) {
            Card card = new Card(Card.Suite.valueOf("S"), Card.CardValue.getCardValue("5"));
            expectedSum += card.value.getGameValue();
            player.addCard(card);
        }
        assertEquals(expectedSum, player.score());
    }

}
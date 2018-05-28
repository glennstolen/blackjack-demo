package my.demo.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void test_draw_card() {
        Player player = new Player("test");

        player.addCard(new Card("S","A",11));

        assertEquals(11, player.score());
    }

    @Test
    void test_score() {
        int expectedSum = 0;

        Player player = new Player("test");

        for (int i = 0; i < 10; i++) {
            Card card = new Card("card"+i, Integer.toString(i), i);
            expectedSum += card.value;
            player.addCard(card);
        }
        assertEquals(expectedSum, player.score());
    }

}
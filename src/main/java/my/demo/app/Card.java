package my.demo.app;

import java.util.Arrays;

public class Card {

    public enum Suite {
        H, D, C, S
    }

    public enum CardValue {
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
        private int gameValue;

        CardValue(String rank, int gameValue) {
            this.rank = rank;
            this.gameValue = gameValue;
        }

        public static CardValue getCardValue(String rank) {

            return Arrays.stream(values())
                    .filter(val -> val.rank.equals(rank))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Not possible to create CardValue with: rank " + rank));
        }

        int getGameValue() {
            return gameValue;
        }

        public String getRank() {
            return rank;
        }
    }

    final Suite suite;

    final CardValue value;

    public Card(Suite suite, CardValue value) {
        this.suite = suite;
        this.value = value;
    }

    @Override
    public String toString() {
        return suite + value.rank ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!suite.equals(card.suite)) return false;
        return value.rank.equals(card.value.rank);
    }

    @Override
    public int hashCode() {
        int result = suite.hashCode();
        result = 31 * result + value.rank.hashCode();
        return result;
    }
}

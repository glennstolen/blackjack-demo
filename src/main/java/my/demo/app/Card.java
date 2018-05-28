package my.demo.app;

public class Card {

    /**
     * C - Clubes
     * D - Diamonds
     * H - Hearts
     * S - Spades
     */
    final String suite;

    /* 2,3,4,5,6,7,8,9,10,J,Q,K,A */
    final String valueAsString;

    /* 2,3,4,5,6,7,8,9,10,11 */
    final int value;

    public Card(String suite, String valueAsString, int value) {
        this.suite = suite;
        this.valueAsString = valueAsString;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +suite + valueAsString +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!suite.equals(card.suite)) return false;
        return valueAsString.equals(card.valueAsString);
    }

    @Override
    public int hashCode() {
        int result = suite.hashCode();
        result = 31 * result + valueAsString.hashCode();
        return result;
    }
}

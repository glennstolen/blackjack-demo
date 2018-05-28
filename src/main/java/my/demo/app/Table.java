package my.demo.app;

public class Table {


    private Player dealer = new Player("dealer");
    private Player player = new Player("sam");

    private CardDeck deck;


    public Table() {
        this.deck = new CardDeck();
    }

    public Table(CardDeck deck) {
       this.deck = deck;
    }


    public Result playGame() {
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());

        /* sam wins when both players starts with Blackjack */
        if (player.score() == 21) {
            return new Result(player.getName(),dealer,player);
        }

        /* dealer wins when both players starts with 22*/
        if (player.score() == 22 && dealer.score() == 22) {
            return new Result(dealer.getName(), dealer, player);
        }

        /* sam must stop drawing cards from the deck if their total reaches 17 or higher */
        while (player.score() < 17) {
            player.addCard(deck.draw());
        }

        /* sam has lost the game if their total is higher than 21 */
        if (player.score() > 21) {
            return new Result(dealer.getName(),dealer,player);
        }

        /* the dealer must stop drawing cards when their total is higher than sam */
        while (dealer.score() < player.score()) {
            dealer.addCard(deck.draw());
        }

        /* the dealer has lost the game if their total is higher than 21 */
        if (dealer.score()>21) {
            return new Result(player.getName(),dealer,player);
        }

        /* determine which player wins the game (highest score wins) */
        if (player.score() > dealer.score()) {
            return new Result(player.getName(),dealer,player);
        } else {
            return new Result(dealer.getName(),dealer,player);
        }
    }

}

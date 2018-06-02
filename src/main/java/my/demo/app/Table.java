package my.demo.app;

import java.util.ArrayList;
import java.util.List;

public class Table {


    private Player dealer = new Player("dealer");

    private List<Player> players = new ArrayList<>();

    private CardDeck deck;


    public Table() {
        this.deck = new CardDeck();
    }

    Table(CardDeck deck) {
       this.deck = deck;
    }

    CardDeck getCardDeck() {
        return this.deck;
    }

    Player getDealer() {
        return dealer;
    }

    boolean addPlayer(Player player) {
        return this.players.add(player);
    }

    List<Player> getPlayers() {
        return players;
    }

    Result getResult() {
        /* player wins when both players starts with Blackjack */
        if (players.iterator().next().score() == 21) {
            return new Result(players.iterator().next().getName(),dealer,players.iterator().next());
        }
        if (dealer.score() == 21) {
            return new Result(dealer.getName(),dealer,players.iterator().next());
        }

        /* dealer wins when both players starts with 22*/
        if (players.iterator().next().score() == 22 && dealer.score() == 22) {
            return new Result(dealer.getName(), dealer, players.iterator().next());
        }

        /* sam has lost the game if their total is higher than 21 */
        if (players.iterator().next().score() > 21) {
            return new Result(dealer.getName(),dealer,players.iterator().next());
        }

        /* the dealer has lost the game if their total is higher than 21 */
        if (dealer.score()>21) {
            return new Result(players.iterator().next().getName(),dealer,players.iterator().next());
        }

        if (players.iterator().next().score()>=17 && dealer.score()>players.iterator().next().score()) {
        /* determine which player wins the game (highest score wins) */
            if (players.iterator().next().score() > dealer.score()) {
                return new Result(players.iterator().next().getName(), dealer, players.iterator().next());
            } else {
                return new Result(dealer.getName(), dealer, players.iterator().next());
            }
        } else {
            return null; // No winner so fare
        }
    }

    /*
    Used by SimpleMain
     */
    public Result playGame() {

        Player player = new Player("sam");
        addPlayer(player);

        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());

        /* sam wins when both players starts with Blackjack */
        if (player.score() == 21) {
            return new Result(player.getName(),dealer,player);
        }
        if (dealer.score() == 21) {
            return new Result(dealer.getName(),dealer,player);
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
        while (dealer.score() <= player.score()) {
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

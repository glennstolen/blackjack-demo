package my.demo.app.interactive;

import my.demo.app.CardDeck;
import my.demo.app.Player;
import my.demo.app.Result;

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

    public CardDeck getCardDeck() {
        return this.deck;
    }

    public Player getDealer() {
        return dealer;
    }

    public boolean addPlayer(Player player) {
        return this.players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Result getResult() {
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
}

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

    public Player getPlayer(String name) {
        return players.stream()
                .filter(p -> p.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Found more than one"));
    }

    public void updateResult() {
        for (Player player : getPlayers()) {
            Result result = getResult(player);
            if (result!=null) {
                player.setResult(result);
            }
        }
    }

    Result getResult(Player player) {
        /* player wins when both players starts with Blackjack */
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


        if (player.score() > 21) {
            return new Result(dealer.getName(),dealer,player);
        }

        /* the dealer has lost the game if their total is higher than 21 */
        if (dealer.score()>21) {
            return new Result(player.getName(),dealer,player);
        }

        if (player.score()>=17 && dealer.score()>player.score()) {
        /* determine which player wins the game (highest score wins) */
            if (player.score() > dealer.score()) {
                return new Result(player.getName(), dealer, player);
            } else {
                return new Result(dealer.getName(), dealer, player);
            }
        } else {
            return null; // No winner so fare
        }
    }
}

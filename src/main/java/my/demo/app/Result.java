package my.demo.app;

public class Result {
    String winner;

    Player dealer;
    Player player;

    public Result(String winner, Player dealer, Player player) {
        this.winner = winner;
        this.dealer = dealer;
        this.player = player;
    }

    public String getWinner() {
        return winner;
    }

    public String getDealerCards() {
        return dealer.getCards();
    }

    public String getPlayerCards() {
        return player.getCards();
    }

    public void printResult() {
        System.out.println(winner);
        System.out.println(player);
        System.out.println(dealer);
    }
}
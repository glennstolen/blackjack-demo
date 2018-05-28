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

    public static Result clone(Result result) {
        Player p = new Player(result.player);
        Player d = new Player((result.dealer));
        return new Result(result.winner, d, p);
    }
}

package my.demo.app;

import my.demo.utils.ResourceUtils;
import org.apache.log4j.Logger;

import java.util.List;


public class MainSimple {

    private static final Logger logger = Logger.getLogger(MainSimple.class);

    public static void main(String[] args) {

        Table blackjackTable;
        try {
            String cardsFile = null;
            if (args.length>0) {
                cardsFile = args[0];
            }

            if (cardsFile != null) {
                List<Card> cards = ResourceUtils.getCardsFromTokenizedFile(cardsFile);
                CardDeck deck = new CardDeck(cards);
                blackjackTable = new Table(deck);
            } else {
                blackjackTable = new Table();
            }

            Result result = blackjackTable.playGame();

            result.printResult();

            logger.info("Demo finished successfully");

        } catch (Exception e) {
            logger.error("Some error occurred", e);
            System.exit(-1);
        }
    }
}

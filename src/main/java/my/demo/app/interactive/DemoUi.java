package my.demo.app.interactive;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import my.demo.app.*;

@Title("Demo")
public class DemoUi extends UI {

    private VerticalLayout layout;
    private TreeGrid<Result> list;

    public static Table table = new Table();

    private Label playerScoreLabel = new Label("");
    private Label dealerScoreLabel = new Label("");


    private Button dealerButton = new Button("Dealer - Draw", event -> {
        Card card = table.getCardDeck().draw();
        table.getDealer().addCard(card);
        setDealerScoreLabel();

        checkResult();
    });

    private Button playerDrawButton = new Button("Player - Draw", event -> {
        Card card = table.getCardDeck().draw();
        table.getPlayers().iterator().next().addCard(card);
        setPlayerScoreLabel();
        checkResult();
    });

    private Button playerHoldButton = new Button("Player - Hold", event -> {

        table.getPlayers().iterator().next().hold();
        playerDrawButton.setEnabled(false);
        dealerButton.setEnabled(true);
        dealerButton.focus();
    });

    private Button newGameButton = new Button("New game", event -> {
        table.addPlayer(new Player("sam"));
        table.getCardDeck().shuffle();
        table.getPlayers().iterator().next().clear();
        table.getDealer().clear();

        drawInitialCards();

        if (table.getPlayers().iterator().next().score()<21) {
            playerDrawButton.setEnabled(true);
            playerHoldButton.setEnabled(true);
            playerDrawButton.focus();
            dealerButton.setEnabled(false);
        }

        setPlayerScoreLabel();
        setDealerScoreLabel();

        checkResult();
    });

    @Override
    protected void init(VaadinRequest request) {
        list = new TreeGrid<>(Result.class);
        list.setItems(ResultList.allResults);

        dealerButton.setEnabled(false);
        playerDrawButton.setEnabled(false);
        playerHoldButton.setEnabled(false);

        layout = new VerticalLayout();

        HorizontalLayout buttons1 = new HorizontalLayout(
                new VerticalLayout(
                        newGameButton
                )
        );

        HorizontalLayout buttons2 = new HorizontalLayout(
                new VerticalLayout(
                        playerDrawButton,
                        playerHoldButton,
                        playerScoreLabel),
                new VerticalLayout(
                        dealerButton,
                        dealerScoreLabel));

        layout.addComponent(buttons1);
        layout.addComponent(buttons2);
        layout.addComponentsAndExpand(list);

        setContent(layout);

    }

    private void setDealerScoreLabel() {
        dealerScoreLabel.setValue(table.getDealer().getDrawnCards() + " (" + Integer.toString(table.getDealer().score()) + ")");
    }

    private void setPlayerScoreLabel() {
        playerScoreLabel.setValue(table.getPlayers().iterator().next().getDrawnCards() + " ("+ Integer.toString(table.getPlayers().iterator().next().score())+")");
    }

    private void drawInitialCards() {
        Card card1 = table.getCardDeck().draw();
        table.getPlayers().iterator().next().addCard(card1);

        Card card2 = table.getCardDeck().draw();
        table.getDealer().addCard(card2);

        Card card3 = table.getCardDeck().draw();
        table.getPlayers().iterator().next().addCard(card3);

        Card card4 = table.getCardDeck().draw();
        table.getDealer().addCard(card4);
    }

    private void checkResult() {
        Result result = table.getResult();
        if (result !=null) { // we have a winner
            ResultList.allResults.add(Result.clone(result));
            list.setItems(ResultList.allResults);
            dealerScoreLabel.setValue("");
            playerScoreLabel.setValue("");
            dealerButton.setEnabled(false);
            playerDrawButton.setEnabled(false);
            playerHoldButton.setEnabled(false);
            newGameButton.setEnabled(true);
            newGameButton.focus();
        } else {
            newGameButton.setEnabled(false);
        }
    }

}

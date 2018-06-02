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

    private Button playerButton = new Button("Player - Draw", event -> {
        Card card = table.getCardDeck().draw();
        table.getPlayers().iterator().next().addCard(card);
        setPlayerScoreLabel();
        checkResult();
    });

    private Button dealerButton = new Button("Dealer - Draw", event -> {
        Card card = table.getCardDeck().draw();
        table.getDealer().addCard(card);
        setDealerScoreLabel();

        checkResult();
    });

    private Button newGameButton = new Button("New game", event -> {
        table.addPlayer(new Player("sam"));
        table.getCardDeck().shuffle();
        table.getPlayers().iterator().next().clear();
        table.getDealer().clear();

        drawInitialCards();
        dealerButton.setEnabled(true);
        playerButton.setEnabled(true);

        setPlayerScoreLabel();
        setDealerScoreLabel();

        checkResult();
    });

    @Override
    protected void init(VaadinRequest request) {
        list = new TreeGrid<>(Result.class);
        list.setItems(ResultList.allResults);

        dealerButton.setEnabled(false);
        playerButton.setEnabled(false);

        layout = new VerticalLayout();

        HorizontalLayout buttons1 = new HorizontalLayout(
                new VerticalLayout(
                        newGameButton
                )
        );

        HorizontalLayout buttons2 = new HorizontalLayout(
                new VerticalLayout(
                        playerButton,
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
            playerButton.setEnabled(false);
            newGameButton.setEnabled(true);
            newGameButton.focus();
        } else {
            newGameButton.setEnabled(false);
            dealerButton.setEnabled(true);
            playerButton.setEnabled(true);
        }
    }

}

package my.demo.app.interactive;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import my.demo.app.*;

@Title("Demo")
public class DemoUi extends UI {

    private VerticalLayout layout;
    private TreeGrid<Result> resultList;
    private TreeGrid<Player> playerList;

    private static Table table = new Table();

    private Label dealerScoreLabel = new Label("");

    private Button dealerButton = new Button("Dealer - Draw", event -> {
        Card card = table.getCardDeck().draw();
        table.getDealer().addCard(card);
        setDealerScoreLabel();

        table.updateResult();
        playerList.setItems(table.getPlayers());
    });

    private TextField newPlayerName = new TextField();

    private Button addPlayerButton = new Button("Add player", event -> {
        Player player = new Player(newPlayerName.getValue());
        table.addPlayer(player);
        playerList.setItems(table.getPlayers());
    });


    private Button newGameButton = new Button("New game", event -> {
        table.getCardDeck().shuffle();

        table.getDealer().clear();
        table.getPlayers().forEach(Player::clear);

        drawInitialCards();

        addPlayerButton.setEnabled(false);

        setDealerScoreLabel();
        table.updateResult();
        playerList.setItems(table.getPlayers());
    });

    @Override
    protected void init(VaadinRequest request) {
        resultList = new TreeGrid<>(Result.class);
        resultList.setItems(ResultList.allResults);

        playerList = new TreeGrid<>(Player.class);
        playerList.setWidth("100%");

        playerList.addColumn(person -> "Draw",
                new ButtonRenderer(clickEvent -> {
                    Card card = table.getCardDeck().draw();
                    ((Player) clickEvent.getItem()).addCard(card);
                    table.updateResult();
                    playerList.setItems(table.getPlayers());
                }));

        playerList.addColumn(person -> "Hold",
                new ButtonRenderer(clickEvent -> {
                    dealerButton.setEnabled(true);
                    dealerButton.focus();

                    ((Player) clickEvent.getItem()).hold();
                    playerList.setItems(table.getPlayers());
                }));

        dealerButton.setEnabled(false);

        layout = new VerticalLayout();

        HorizontalLayout addPlayerArea = new HorizontalLayout(
                newPlayerName,
                addPlayerButton
        );

        HorizontalLayout newGameArea = new HorizontalLayout(
                newGameButton
        );

        HorizontalLayout dealerArea = new HorizontalLayout(
                        dealerButton,
                        dealerScoreLabel);

        layout.addComponent(addPlayerArea);
        layout.addComponentsAndExpand(playerList);

        layout.addComponent(newGameArea);
        layout.addComponent(dealerArea);
        layout.addComponentsAndExpand(resultList);

        setContent(layout);

    }

    private void setDealerScoreLabel() {
        dealerScoreLabel.setValue(table.getDealer().getDrawnCards() + " (" + Integer.toString(table.getDealer().score()) + ")");
    }

    private void drawInitialCards() {
        for (Player p : table.getPlayers()) {
            p.addCard(table.getCardDeck().draw());
        }

        table.getDealer().addCard(table.getCardDeck().draw());

        for (Player p : table.getPlayers()) {
            p.addCard(table.getCardDeck().draw());
        }

        table.getDealer().addCard(table.getCardDeck().draw());
    }

}

package my.demo.app;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

@Title("Demo")
public class DemoUi extends UI {

    private VerticalLayout layout;
    private TreeGrid<Result> list;

    public static Table table = new Table();

    @Override
    protected void init(VaadinRequest request) {
        list = new TreeGrid<>(Result.class);
        list.setItems(ResultList.allResults);

        Label scorePlayer = new Label("");
        Label scoreDealer = new Label("");
        layout = new VerticalLayout();
        HorizontalLayout buttons1 = new HorizontalLayout(
                new VerticalLayout(
                        new Button("New game", event -> {
                            table.addPlayer(new Player("sam"));
                            table.getCardDeck().shuffle();
                            table.getPlayers().iterator().next().clear();
                            table.getDealer().clear();

                            Card card1 = table.getCardDeck().draw();
                            table.getPlayers().iterator().next().addCard(card1);

                            Card card2 = table.getCardDeck().draw();
                            table.getDealer().addCard(card2);

                            Card card3 = table.getCardDeck().draw();
                            table.getPlayers().iterator().next().addCard(card3);

                            Card card4 = table.getCardDeck().draw();
                            table.getDealer().addCard(card4);

                            scorePlayer.setValue(Integer.toString(table.getPlayers().iterator().next().score()));
                            scoreDealer.setValue(Integer.toString(table.getDealer().score()));

                            Result result = table.getResult();
                            if (result !=null) {
                                ResultList.allResults.add(Result.clone(result));
                                list.setItems(ResultList.allResults);
                            }
                        })
                )
        );

        HorizontalLayout buttons2 = new HorizontalLayout(
                new VerticalLayout(
                        new Button("Player - Draw", event -> {
                            Card card = table.getCardDeck().draw();
                            table.getPlayers().iterator().next().addCard(card);
                            scorePlayer.setValue(Integer.toString(table.getPlayers().iterator().next().score()));

                            Result result = table.getResult();
                            if (result !=null) {
                                ResultList.allResults.add(Result.clone(result));
                                list.setItems(ResultList.allResults);
                            }
                        }),
                        scorePlayer),
                new VerticalLayout(
                        new Button("Dealer - Draw", event -> {
                            Card card = table.getCardDeck().draw();
                            table.getDealer().addCard(card);
                            scoreDealer.setValue(Integer.toString(table.getDealer().score()));

                            Result result = table.getResult();
                            if (result !=null) {
                                ResultList.allResults.add(Result.clone(result));
                                list.setItems(ResultList.allResults);
                            }
                        }),
                        scoreDealer));

        layout.addComponent(buttons1);
        layout.addComponent(buttons2);
        layout.addComponentsAndExpand(list);

        setContent(layout);

    }

}

package my.demo.app;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("Demo")
public class DemoUi extends UI {

    private VerticalLayout content;
    private TreeGrid<Result> list;

    @Override
    protected void init(VaadinRequest request) {
        list = new TreeGrid<>(Result.class);
        list.setItems(ResultList.allResults);
        content = new VerticalLayout(
                new Button("Play game", event -> {
                    Table table = new Table();
                    Result result = table.playGame();

                    ResultList.allResults.add(result);
                    list.setItems(ResultList.allResults);
                })
        );
        content.addComponentsAndExpand(list);
        content.setSizeFull();
        setContent(content);

    }

}

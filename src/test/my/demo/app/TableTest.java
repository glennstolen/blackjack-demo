package my.demo.app;

import my.demo.app.simple.Table;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertNotNull;

class TableTest {
    @Test
    void startGame() {
        Table table = new Table();
        Result result = table.playGame();
        assertNotNull(result);
        result.printResult();
    }

}
package TicTacToe.service.ai;

import TicTacToe.model.Cell;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static TicTacToe.service.ai.AIService.turn;

/**
 * @author Lelental on 05.05.2017.
 */
public class EasyBot implements ICanMakeMove {

    EasyBot(List<Cell> cells) {
        this.cells = cells;
    }

    private List<Cell> cells;
    private static int cellId;

    public int getCellId() {
        return cellId;
    }

    public void makeMove() {
        if (turn % 2 != 0) {
            do {
                if (turn >= 9) {
                    break;
                }
                cellId = ThreadLocalRandom.current().nextInt(0, 9);
            } while (cells.get(cellId).isPainted());

            turn++;

        }
    }
}

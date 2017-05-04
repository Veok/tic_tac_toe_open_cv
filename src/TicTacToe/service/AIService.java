package TicTacToe.service;

import TicTacToe.model.Cell;
import TicTacToe.model.Mark;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Lelental on 21.04.2017.
 */
class AIService {

    private List<Cell> cellList;
    private static int cellId;
    public static int turn = 0;

    public AIService(List<Cell> cellList) {
        this.cellList = cellList;
    }


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
            } while (cellList.get(cellId).isPainted());

            turn++;

        }
      
    }


}




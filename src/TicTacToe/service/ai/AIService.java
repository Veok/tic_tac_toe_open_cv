package TicTacToe.service.ai;

import TicTacToe.model.Cell;

import java.util.List;

/**
 * @author Lelental on 05.05.2017.
 */
public class AIService {

    public static int turn = 0;
    private EasyBot easyBot;

    public AIService(List<Cell> cellList) {
        this.easyBot = new EasyBot(cellList);
    }

    public EasyBot getEasyBot() {
        return easyBot;
    }

}

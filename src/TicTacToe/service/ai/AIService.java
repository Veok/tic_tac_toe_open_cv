package TicTacToe.service.ai;

import TicTacToe.model.Cell;
import TicTacToe.service.IRestartService;

import java.util.List;

/**
 * @author Lelental on 05.05.2017.
 */
public class AIService implements IRestartService {

    public static int turn = 0;
    private EasyBot easyBot;

    public AIService(List<Cell> cellList) {
        this.easyBot = new EasyBot(cellList);
    }

    @Override
    public void restart() {
        turn = 0;
    }

    public EasyBot getEasyBot() {
        return easyBot;
    }


}

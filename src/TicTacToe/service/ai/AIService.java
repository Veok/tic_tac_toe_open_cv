package TicTacToe.service.ai;

import TicTacToe.model.Cell;
import TicTacToe.service.IRestartService;

import java.util.List;

/**
 * @author Lelental on 05.05.2017.
 */
public class AIService implements IRestartService {

    public static int turn = 0;
    private AIBot AIBot;

    public AIService(List<Cell> cellList) {
        this.AIBot = new AIBot(cellList);
    }

    @Override
    public void restart() {
        turn = 0;
    }

    public AIBot getAIBot() {
        return AIBot;
    }


}

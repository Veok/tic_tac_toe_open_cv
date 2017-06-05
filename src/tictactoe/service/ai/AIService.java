package tictactoe.service.ai;

import tictactoe.model.Cell;
import tictactoe.service.IRestartService;

import java.util.List;

/**
 * @author Lelental on 05.05.2017.
 */
public class AIService implements IRestartService {

    public static int turn = 0;
    private AIBot aiBot;

    public AIService(List<Cell> cellList) {
        this.aiBot = new AIBot(cellList);
    }

    @Override
    public void restart() {
        turn = 0;
    }

    public AIBot getAiBot() {
        return aiBot;
    }

    public void makeMove(){
        aiBot.makeMove();
    }
}

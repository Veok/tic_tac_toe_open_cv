package tictactoe.service.ai;

import tictactoe.model.Cell;
import tictactoe.model.Mark;
import tictactoe.service.coordinates.CellCoordinate;
import tictactoe.service.coordinates.WinLineCoordinate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static tictactoe.service.ai.AIService.turn;

/**
 * @author Lelental on 05.05.2017.
 */
public class AIBot {

    AIBot(List<Cell> cells) {
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
                if (turn == 1) {
                    cellId = ThreadLocalRandom.current().nextInt(0, 9);
                } else {
                    cellId = findBestMove();
                }
            } while (cells.get(cellId).isPainted());
            turn++;
        }
    }

    private boolean win(Mark mark) {
        for (int i = 0; i < 3; i++) {
            if (WinLineCoordinate.rowWin(i, mark)) {
                return true;
            }
            if (WinLineCoordinate.columnWin(i, mark)) {
                return true;
            }
        }
        return WinLineCoordinate.firstCrossWin(mark) || WinLineCoordinate.secondCrossWin(mark);

    }

    private int evaluate() {

        if (win(Mark.Nought)) {
            return -10;
        } else if (win(Mark.Cross)) {
            return 10;
        }
        return 0;
    }

    private int minMax(int depth, boolean isMax) {

        int score = evaluate();

        if (score == 10) {
            return score;
        }
        if (score == -10) {
            return score;
        }
        if (!isMovesLeft()) {
            return 0;
        }
        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (CellCoordinate.getMarkBoard()[i][j] == null) {
                        CellCoordinate.setMarkBoard(i, j, Mark.Cross);
                        best = Math.max(best, minMax(depth + 1, false));
                        CellCoordinate.setMarkBoard(i, j, null);
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (CellCoordinate.getMarkBoard()[i][j] == null) {
                        CellCoordinate.setMarkBoard(i, j, Mark.Nought);
                        best = Math.min(best, minMax(depth + 1, true));
                        CellCoordinate.setMarkBoard(i, j, null);
                    }
                }
            }
            return best;
        }
    }

    private int findBestMove() {

        int best = -1000;
        int id = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (CellCoordinate.getMarkBoard()[i][j] == null) {
                    CellCoordinate.setMarkBoard(i, j, Mark.Cross);
                    int move = minMax(0, false);
                    CellCoordinate.setMarkBoard(i, j, null);

                    if (move > best) {
                        for (Cell cell : cells) {
                            if (cell.getRow() == i && cell.getColumn() == j) {
                                id = cell.getId();
                            }
                        }
                    }

                }
            }
        }
        return id;
    }

    private boolean isMovesLeft() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (CellCoordinate.getMarkBoard()[i][j] == null)
                    return true;
        return false;
    }
}





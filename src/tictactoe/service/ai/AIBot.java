package tictactoe.service.ai;

import tictactoe.model.Cell;
import tictactoe.model.Mark;
import tictactoe.service.coordinates.CellCoordinate;
import tictactoe.service.coordinates.WinLineCoordinate;

import java.util.List;

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

                cellId = findBestMove();

            } while (cells.get(cellId).isPainted());
            turn++;
        }

    }

    private boolean win(Mark mark) {

        return WinLineCoordinate.rowWin(0, mark) || WinLineCoordinate.rowWin(1, mark)
                || WinLineCoordinate.rowWin(2, mark) || WinLineCoordinate.columnWin(0, mark)
                || WinLineCoordinate.columnWin(1, mark) || WinLineCoordinate.columnWin(2, mark)
                || WinLineCoordinate.firstCrossWin(mark) || WinLineCoordinate.secondCrossWin(mark);

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
            return  depth;
        }
        if (score == -10) {
            return score;
        }
        if (!isMovesLeft()) {
            return 0;
        }
        if (isMax) {
            int bestCross = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (CellCoordinate.getMarkBoard()[i][j] == null) {
                        CellCoordinate.setMarkBoard(i, j, Mark.Cross);
                        bestCross = Math.max(bestCross, minMax(depth + 1, false));
                        CellCoordinate.setMarkBoard(i, j, null);
                    }
                }
            }
            return bestCross;
        } else {
            int bestNought = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (CellCoordinate.getMarkBoard()[i][j] == null) {
                        CellCoordinate.setMarkBoard(i, j, Mark.Nought);
                        bestNought = Math.min(bestNought, minMax(depth + 1, true));
                        CellCoordinate.setMarkBoard(i, j, null);
                    }
                }
            }
            return bestNought;
        }
    }

    public int findBestMove() {

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
                            System.out.println("move value: "+ move
                                    + " i: " + i + " j: " + j + " row: " + cell.getRow() + " column: "
                                    + cell.getColumn()
                                    + " id: " + cell.getId());
                            best = move;
                            if (i == cell.getRow() && j == cell.getColumn()) {
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





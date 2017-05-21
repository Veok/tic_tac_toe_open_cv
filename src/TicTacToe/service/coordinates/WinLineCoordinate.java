package TicTacToe.service.coordinates;

import TicTacToe.model.Mark;
import TicTacToe.service.GameService;

import static TicTacToe.service.coordinates.CellCoordinate.getMarkBoard;

/**
 * @author Lelental on 05.05.2017.
 */
public class WinLineCoordinate {

    public static boolean winPositions(int row, int column, Mark mark) {

        if (mark != null) {
            if (rowWin(row, mark)) {
                GameService.setGameOver();
                return true;

            } else if (columnWin(column, mark)) {
                GameService.setGameOver();
                return true;

            } else if (firstCrossWin(row, column, mark)) {
                GameService.setGameOver();
                return true;

            } else if (secondCrossWin(row, column, mark)) {
                GameService.setGameOver();
                return true;
            }
        }
        return false;
    }

    private static boolean rowWin(int row, Mark mark) {
        return getMarkBoard()[row][0] == mark && getMarkBoard()[row][1] == mark
                && getMarkBoard()[row][2] == mark;
    }

    private static boolean columnWin(int column, Mark mark) {
        return getMarkBoard()[0][column] == mark && getMarkBoard()[1][column] == mark
                && getMarkBoard()[2][column] == mark;
    }

    private static boolean firstCrossWin(int row, int column, Mark mark) {
        return row == 1 && column == 1 && getMarkBoard()[0][0] == mark
                && getMarkBoard()[row][column] == mark && getMarkBoard()[2][2] == mark;
    }

    private static boolean secondCrossWin(int row, int column, Mark mark) {
        return row == 1 && column == 1 && getMarkBoard()[2][0] == mark
                && getMarkBoard()[row][column] == mark && getMarkBoard()[0][2] == mark;
    }
}

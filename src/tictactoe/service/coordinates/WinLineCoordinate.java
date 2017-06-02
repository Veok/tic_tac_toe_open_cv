package tictactoe.service.coordinates;

import tictactoe.model.Mark;
import tictactoe.service.GameService;

import static tictactoe.service.coordinates.CellCoordinate.getMarkBoard;

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

            } else if (firstCrossWin(mark)) {
                GameService.setGameOver();
                return true;

            } else if (secondCrossWin(mark)) {
                GameService.setGameOver();
                return true;
            }
        }
        return false;
    }

    public static boolean rowWin(int row, Mark mark) {
        return getMarkBoard()[row][0] == mark && getMarkBoard()[row][1] == mark
                && getMarkBoard()[row][2] == mark;
    }

    public static boolean columnWin(int column, Mark mark) {
        return getMarkBoard()[0][column] == mark && getMarkBoard()[1][column] == mark
                && getMarkBoard()[2][column] == mark;
    }

    public static boolean firstCrossWin(Mark mark) {
        return getMarkBoard()[0][0] == mark
                && getMarkBoard()[1][1] == mark && getMarkBoard()[2][2] == mark;
    }

    public static boolean secondCrossWin(Mark mark) {
        return getMarkBoard()[2][0] == mark
                && getMarkBoard()[1][1] == mark && getMarkBoard()[0][2] == mark;
    }
}

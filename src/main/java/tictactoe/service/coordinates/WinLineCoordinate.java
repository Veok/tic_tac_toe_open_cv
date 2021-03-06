package tictactoe.service.coordinates;

import tictactoe.model.Mark;
import tictactoe.service.GameService;

import static tictactoe.service.coordinates.CellCoordinate.getMarkBoard;

/**
 * @author Lelental on 05.05.2017.
 */
class WinLineCoordinate {

    static boolean winPositionsToPaint(int row, int column, Mark mark) {

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

    static boolean checkIfWin(Mark mark) {

        return WinLineCoordinate.rowWin(0, mark) || WinLineCoordinate.rowWin(1, mark)
                || WinLineCoordinate.rowWin(2, mark) || WinLineCoordinate.columnWin(0, mark)
                || WinLineCoordinate.columnWin(1, mark) || WinLineCoordinate.columnWin(2, mark)
                || WinLineCoordinate.firstCrossWin(1, 1, mark) || WinLineCoordinate.secondCrossWin(1, 1, mark);

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
                && getMarkBoard()[1][1] == mark && getMarkBoard()[2][2] == mark;
    }

    private static boolean secondCrossWin(int row, int column, Mark mark) {
        return row == 1 && column == 1 && getMarkBoard()[2][0] == mark
                && getMarkBoard()[1][1] == mark && getMarkBoard()[0][2] == mark;
    }
}

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
            if (getMarkBoard()[row][0] == mark && getMarkBoard()[row][1] == mark
                    && getMarkBoard()[row][2] == mark) {
                GameService.setGameOver();
                return true;

            } else if (getMarkBoard()[0][column] == mark && getMarkBoard()[1][column] == mark
                    && getMarkBoard()[2][column] == mark) {
                GameService.setGameOver();
                return true;

            } else if (row == 1 && column == 1 && getMarkBoard()[0][0] == mark
                    && getMarkBoard()[row][column] == mark && getMarkBoard()[2][2] == mark) {
                GameService.setGameOver();
                return true;

            } else if (row == 1 && column == 1 && getMarkBoard()[2][0] == mark
                    && getMarkBoard()[row][column] == mark && getMarkBoard()[0][2] == mark) {
                GameService.setGameOver();
                return true;
            }
        }
        return false;
    }


}

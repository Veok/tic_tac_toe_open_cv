package TicTacToe.service.coordinates;

import TicTacToe.model.Mark;
import TicTacToe.service.GameService;

import static TicTacToe.service.coordinates.CellCoordinate.getCellArray;

/**
 * @author Lelental on 05.05.2017.
 */
public class WinLineCoordinate {

    public static boolean winPositions(int row, int column, Mark mark) {

        if (mark != null) {
            if (getCellArray()[row][0] == mark && getCellArray()[row][1] == mark
                    && getCellArray()[row][2] == mark) {
                GameService.setGameOver();
                return true;

            } else if (getCellArray()[0][column] == mark && getCellArray()[1][column] == mark
                    && getCellArray()[2][column] == mark) {
                GameService.setGameOver();
                return true;

            } else if (row == 1 && column == 1 && getCellArray()[0][0] == mark
                    && getCellArray()[row][column] == mark && getCellArray()[2][2] == mark) {
                GameService.setGameOver();
                return true;

            } else if (row == 1 && column == 1 && getCellArray()[2][0] == mark
                    && getCellArray()[row][column] == mark && getCellArray()[0][2] == mark) {
                GameService.setGameOver();
                return true;
            }
        }
        return false;
    }


}

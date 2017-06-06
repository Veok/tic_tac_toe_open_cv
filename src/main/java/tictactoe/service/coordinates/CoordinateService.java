package tictactoe.service.coordinates;

import tictactoe.model.Cell;
import tictactoe.model.Mark;
import tictactoe.service.IRestartService;

import java.util.ArrayList;

/**
 * @author Lelental on 05.05.2017.
 */
public class CoordinateService implements IRestartService {

    private CellCoordinate cellCoordinate = new CellCoordinate();

    public CoordinateService() {
    }

    @Override
    public void restart() {
        cellCoordinate.resetMarkBoard();
        for (Cell cell : cellCoordinate.getCells()) {
            cell.setPainted(false);
            cell.setMark(null);
        }
    }

    public ArrayList<Cell> getCells(){
        return cellCoordinate.getCells();
    }
    public static boolean winPositionsToPaint(int row, int column, Mark mark) {
        return WinLineCoordinate.winPositionsToPaint(row, column, mark);
    }

    public static boolean checkIfWin(Mark mark) {
        return WinLineCoordinate.checkIfWin(mark);
    }

    public static void setMarkBoard(int row, int column, Mark mark) {
        CellCoordinate.setMarkBoard(row,column,mark);
    }

    public static Mark[][] getMarkBoard() {
        return CellCoordinate.getMarkBoard();
    }


}

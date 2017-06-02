package tictactoe.service.coordinates;

import tictactoe.model.Cell;
import tictactoe.service.IRestartService;

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

    public CellCoordinate getCellCoordinate() {
        return cellCoordinate;
    }


}
